package com.alami.demo.transaction.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "money")
public class MoneyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Long amount;

	private Date last_calibrate;

	public MoneyEntity(Long id, Long amount, Date last_calibrate) {
		super();
		this.id = id;
		this.amount = amount;
		this.last_calibrate = last_calibrate;
	}

	public MoneyEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getLast_calibrate() {
		return last_calibrate;
	}

	public void setLast_calibrate(Date last_calibrate) {
		this.last_calibrate = last_calibrate;
	}

	@Override
	public String toString() {
		return "MoneyEntity [id=" + id + ", amount=" + amount + ", last_calibrate=" + last_calibrate + "]";
	}

}
