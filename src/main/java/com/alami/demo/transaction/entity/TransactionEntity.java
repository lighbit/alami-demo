package com.alami.demo.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alami.demo.base.BaseEntity;
import com.alami.demo.user.entity.UserEntity;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private UserEntity user;

	@Column
	private Long amount;

	@Column
	private String type;

	public TransactionEntity() {
		// TODO Auto-generated constructor stub
	}

	public TransactionEntity(UserEntity user, Long amount, String type) {
		super();
		this.user = user;
		this.amount = amount;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TransactionEntity [id=" + id + ", user=" + user + ", amount=" + amount + ", type=" + type + "]";
	}

}
