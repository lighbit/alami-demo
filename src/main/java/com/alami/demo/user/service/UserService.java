package com.alami.demo.user.service;

import org.springframework.http.ResponseEntity;

import com.alami.demo.base.MessageResponse;
import com.alami.demo.user.entity.UserRequest;

public interface UserService {

	ResponseEntity<MessageResponse> saveUser(UserRequest request);

}
