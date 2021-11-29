package com.alami.demo.transaction.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alami.demo.base.Constanta;
import com.alami.demo.base.MessageResponse;
import com.alami.demo.transaction.entity.MoneyEntity;
import com.alami.demo.transaction.entity.TransactionEntity;
import com.alami.demo.transaction.repository.MoneyRepository;
import com.alami.demo.transaction.repository.TransactionRepository;
import com.alami.demo.transaction.service.TransactionService;
import com.alami.demo.user.entity.TransactionRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/transaction")
public class TransactionController {

	@Autowired
	TransactionRepository transactionRepo;

	@Autowired
	TransactionService transactionService;

	@Autowired
	MoneyRepository moneyRepo;

	@PostMapping("/incoming")
	public @ResponseBody ResponseEntity<MessageResponse> incoming(@Valid @RequestBody TransactionRequest request) {
		ResponseEntity<MessageResponse> message = transactionService.saveTransactionIncoming(request);

		return message;
	}

	@PostMapping("/outgoing")
	public @ResponseBody ResponseEntity<MessageResponse> outgoing(@Valid @RequestBody TransactionRequest request) {
		ResponseEntity<MessageResponse> message = transactionService.saveTransactionOutgoing(request);

		return message;
	}

	@GetMapping("/all")
	public @ResponseBody List<TransactionEntity> findAll() {
		List<TransactionEntity> list = transactionRepo.findAll();

		return list;
	}

	@GetMapping("/{idTrx}")
	public @ResponseBody TransactionEntity findByID(@PathVariable("idTrx") Long id) {

		return transactionRepo.findById(id).get();
	}

	@DeleteMapping("/{idTrx}")
	public @ResponseBody ResponseEntity<MessageResponse> deleteID(@PathVariable("idTrx") Long id) {
		TransactionEntity entity = transactionRepo.findById(id).get();
		entity.setStatus(Constanta.DELETED.toString());
		entity.setUpdated_by("System");
		entity.setUpdated_at(new Date());
		transactionRepo.save(entity);

		return ResponseEntity.ok(new MessageResponse("User Deleted successfully!"));
	}
	
	@GetMapping("/money")
	public @ResponseBody List<MoneyEntity> getMoney() {
		List<MoneyEntity> list = moneyRepo.findAll();

		return list;
	}

}
