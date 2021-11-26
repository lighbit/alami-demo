package com.alami.demo.base;

import java.util.Date;

public class UserLog {

	private String id;
	private String agentid;
	private String name;
	private Long amount;
	private String type;
	public Date created_at;
	public String created_by;

	public UserLog(String agentid, String name, Long amount, String type, Date created_at, String created_by) {
		super();
		this.agentid = agentid;
		this.name = name;
		this.amount = amount;
		this.type = type;
		this.created_at = created_at;
		this.created_by = created_by;
	}

	public UserLog() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	@Override
	public String toString() {
		return "UserLog [id=" + id + ", agentid=" + agentid + ", name=" + name + ", amount=" + amount + ", type=" + type
				+ ", created_at=" + created_at + ", created_by=" + created_by + "]";
	}

}
