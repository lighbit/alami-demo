package com.alami.demo.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alami.demo.transaction.entity.MoneyEntity;

@Repository
public interface MoneyRepository extends JpaRepository<MoneyEntity, Long> {

	@Query(value = "SELECT * FROM money limit 1", nativeQuery = true)
	MoneyEntity findFirst();

}
