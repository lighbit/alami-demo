package com.alami.demo.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alami.demo.base.BaseEntity;

@Entity
@Table(name = "Customer")
public class UserEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String agentid;

	@Column
	private String name;

	@Column
	private Date born;

	@Column
	private String address;

	@Column
	private String balance;

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String agentid, String name, Date born, String address, String balance) {
		super();
		this.agentid = agentid;
		this.name = name;
		this.born = born;
		this.address = address;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", agentid=" + agentid + ", name=" + name + ", born=" + born + ", address="
				+ address + ", balance=" + balance + "]";
	}

}
