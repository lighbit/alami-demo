package com.alami.demo.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alami.demo.transaction.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	List<TransactionEntity> findByType(String type);

	List<TransactionEntity> findAllByStatus(String status);

}
