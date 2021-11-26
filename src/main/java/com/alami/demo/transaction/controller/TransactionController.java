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
import com.alami.demo.transaction.entity.TransactionEntity;
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
		List<TransactionEntity> list = transactionRepo.findAllByStatus(Constanta.ACTIVE.toString());

		return list;
	}

	@GetMapping("/{idUser}")
	public @ResponseBody TransactionEntity findByID(@PathVariable("idUser") Long id) {

		return transactionRepo.findById(id).get();
	}

	@DeleteMapping("/{idUser}")
	public @ResponseBody ResponseEntity<MessageResponse> deleteID(@PathVariable("idUser") Long id) {
		TransactionEntity entity = transactionRepo.findById(id).get();
		entity.setStatus(Constanta.DELETED.toString());
		entity.setUpdated_by("System");
		entity.setUpdated_at(new Date());
		transactionRepo.save(entity);

		return ResponseEntity.ok(new MessageResponse("User Deleted successfully!"));
	}

}
