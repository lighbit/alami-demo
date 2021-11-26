package com.alami.demo.user.controller;

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
import com.alami.demo.user.entity.UserEntity;
import com.alami.demo.user.entity.UserRequest;
import com.alami.demo.user.repository.UserRepository;
import com.alami.demo.user.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public @ResponseBody ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody UserRequest request) {
		ResponseEntity<MessageResponse> message = userService.saveUser(request);

		return message;
	}

	@GetMapping("/all")
	public @ResponseBody List<UserEntity> findAll() {
		List<UserEntity> list = userRepo.findAllByStatus(Constanta.ACTIVE.toString());

		return list;
	}

	@GetMapping("/{idUser}")
	public @ResponseBody UserEntity findByID(@PathVariable("idUser") Long id) {

		return userRepo.findById(id).get();
	}

	@DeleteMapping("/{idUser}")
	public @ResponseBody ResponseEntity<MessageResponse> deleteID(@PathVariable("idUser") Long id) {
		UserEntity entity = userRepo.findById(id).get();
		entity.setStatus(Constanta.DELETED.toString());
		entity.setUpdated_by("System");
		entity.setUpdated_at(new Date());
		userRepo.save(entity);

		return ResponseEntity.ok(new MessageResponse("User Deleted successfully!"));
	}

}
