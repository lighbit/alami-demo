package com.alami.demo.user.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alami.demo.base.Constanta;
import com.alami.demo.base.MessageResponse;
import com.alami.demo.user.entity.UserEntity;
import com.alami.demo.user.entity.UserRequest;
import com.alami.demo.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public ResponseEntity<MessageResponse> saveUser(UserRequest request) {

		if (request.getId() == null) {

			String agentId;
			while (true) {

				Random rand = new Random();
				int rand_int1 = rand.nextInt(1000);
				String generate = "AG-" + Integer.toString(rand_int1);

				boolean cekAgentId = userRepo.existsByAgentid(generate);

				if (!cekAgentId) {
					agentId = generate;
					break;
				}
			}
			UserEntity userEntity = new UserEntity(agentId, request.getName(), request.getBorn(), request.getAddress(),
					"0");
			userEntity.setCreated_at(new Date());
			userEntity.setCreated_by("System");
			userEntity.setStatus(Constanta.ACTIVE.toString());
			userRepo.save(userEntity);

			return ResponseEntity.ok(new MessageResponse("User Registered successfully!"));

		} else {

			UserEntity userEntity = userRepo.findById(request.getId()).get();
			BeanUtils.copyProperties(request, userEntity);
			userEntity.setUpdated_at(new Date());
			userEntity.setUpdated_by("System");
			userRepo.save(userEntity);

			return ResponseEntity.ok(new MessageResponse("User Update successfully!"));
		}

	}

}
