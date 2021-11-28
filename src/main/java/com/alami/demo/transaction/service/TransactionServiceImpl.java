package com.alami.demo.transaction.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alami.demo.base.Constanta;
import com.alami.demo.base.MessageResponse;
import com.alami.demo.base.UserLog;
import com.alami.demo.transaction.entity.MoneyEntity;
import com.alami.demo.transaction.entity.TransactionEntity;
import com.alami.demo.transaction.repository.MoneyRepository;
import com.alami.demo.transaction.repository.TransactionRepository;
import com.alami.demo.user.entity.TransactionRequest;
import com.alami.demo.user.entity.UserEntity;
import com.alami.demo.user.repository.UserRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class TransactionServiceImpl implements TransactionService {

	@Value("${limit.money}")
	private Long limit;

	@Autowired
	TransactionRepository transactionRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	MoneyRepository moneyRepo;

	@Autowired
	KafkaTemplate<String, Object> template;

	@Override
	public ResponseEntity<MessageResponse> saveTransactionIncoming(TransactionRequest request) {

		UserEntity userEntity = userRepo.findByAgentid(request.getAgentid()).get();

		try {

			if (userEntity == null) {

				return ResponseEntity.badRequest()
						.body(new MessageResponse("Agent Id Cannot be found on All customer"));
			} else {

				Long balance = Long.parseLong(userEntity.getBalance()) + Long.parseLong(request.getAmount());

				/* SAVE BALANCE USER */
				userEntity.setBalance(Long.toString(balance));
				userEntity = userRepo.save(userEntity);

				/* SAVE TRANSACTION */
				TransactionEntity transaction = new TransactionEntity(userEntity, Long.parseLong(request.getAmount()),
						request.getType());
				transaction.setCreated_at(request.getDate());
				transaction.setCreated_by("System");
				transaction.setType(Constanta.INCOMING.toString());
				transaction = transactionRepo.save(transaction);

				/* SAVE MONEY KOPRASI */
				List<MoneyEntity> getList = moneyRepo.findAll();
				if (getList.size() > 0) {
					MoneyEntity money = getList.get(0);
					money.setAmount(money.getAmount() + Long.parseLong(request.getAmount()));
					money.setLast_calibrate(new Date());
					moneyRepo.save(money);
				} else {
					MoneyEntity moneyFirst = new MoneyEntity();
					moneyFirst.setAmount(Long.parseLong(request.getAmount()));
					moneyFirst.setLast_calibrate(new Date());
					moneyRepo.save(moneyFirst);
				}

				/* SEND LOG */
				UserLog userLog = new UserLog(userEntity.getAgentid(), userEntity.getName(),
						Long.parseLong(userEntity.getBalance()), transaction.getType(), new Date(), "System");

				template.send("customer-log", userLog);

				return ResponseEntity.ok(new MessageResponse("Transaction Has been Succesfully"));
			}

		} catch (Exception e) {

			throw new RuntimeException("Transaction cannot be succesfully because -> " + e.getMessage());
		}

	}

	@Override
	public ResponseEntity<MessageResponse> saveTransactionOutgoing(TransactionRequest request) {

		UserEntity userEntity = userRepo.findByAgentid(request.getAgentid()).get();

		try {

			if (userEntity == null) {

				return ResponseEntity.badRequest()
						.body(new MessageResponse("Agent Id Cannot be found on All customer"));
			} else {

				if (Long.parseLong(userEntity.getBalance()) <= limit) {
					return ResponseEntity.badRequest().body(
							new MessageResponse("Cannot outgoing transaction because balance less than -" + limit));
				} else {

					List<MoneyEntity> getList = moneyRepo.findAll();
					MoneyEntity moneyEntity = new MoneyEntity();
					if (getList.size() > 0) {
						moneyEntity = getList.get(0);
					} else {
						moneyEntity.setAmount(Long.parseLong("0"));
					}

					if (moneyEntity.getAmount() > Long.parseLong(request.getAmount())) {

						Long balance = Long.parseLong(userEntity.getBalance()) - Long.parseLong(request.getAmount());

						/* SAVE BALANCE USER */
						userEntity.setBalance(Long.toString(balance));
						userRepo.save(userEntity);

						/* SAVE TRANSACTION */
						TransactionEntity transaction = new TransactionEntity(userEntity,
								Long.parseLong(request.getAmount()), request.getType());
						transaction.setCreated_at(request.getDate());
						transaction.setCreated_by("System");
						transaction.setType(Constanta.OUTGOING.toString());
						transaction = transactionRepo.save(transaction);

						/* SAVE MONEY KOPRASI */
						moneyEntity.setAmount(moneyEntity.getAmount() - Long.parseLong(request.getAmount()));
						moneyRepo.save(moneyEntity);

						/* SEND LOG */
						UserLog userLog = new UserLog(userEntity.getAgentid(), userEntity.getName(),
								Long.parseLong(userEntity.getBalance()), transaction.getType(), new Date(), "System");

						template.send("customer-log", userLog);

						return ResponseEntity.ok(new MessageResponse("Transaction Has been Succesfully"));

					} else {

						return ResponseEntity.badRequest().body(new MessageResponse("Sorry, we out of money :("));
					}

				}

			}

		} catch (Exception e) {
			throw new RuntimeException("Transaction cannot be succesfully because -> " + e.getMessage());
		}
	}

//	@Scheduled(cron = "* * * ? * *")
	public void calibrateMoney() {
		Long money = Long.parseLong("0");
		List<MoneyEntity> getList = moneyRepo.findAll();
		if (getList.size() > 0) {

			MoneyEntity moneyEntity = getList.get(0);

			List<TransactionEntity> listTransaction = transactionRepo.findAll();
			for (TransactionEntity transactionEntity : listTransaction) {
				money += transactionEntity.getAmount();
			}
			moneyEntity.setAmount(money);
			moneyEntity.setLast_calibrate(new Date());
			moneyRepo.save(moneyEntity);
		}
	}

}
