package com.alami.demo.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alami.demo.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByAgentid(String agentid);

	List<UserEntity> findByName(String name);

	List<UserEntity> findAllByStatus(String status);

	boolean existsByAgentid(String agentid);

}
