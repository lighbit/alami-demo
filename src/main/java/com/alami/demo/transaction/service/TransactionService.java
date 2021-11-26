package com.alami.demo.transaction.service;

import org.springframework.http.ResponseEntity;

import com.alami.demo.base.MessageResponse;
import com.alami.demo.user.entity.TransactionRequest;

public interface TransactionService {

	ResponseEntity<MessageResponse> saveTransactionIncoming(TransactionRequest request);
	
	ResponseEntity<MessageResponse> saveTransactionOutgoing(TransactionRequest request);
	
	void calibrateMoney();

}
