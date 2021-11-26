package com.alami.demo.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	@Column
	public Date created_at;

	@Column
	public Date updated_at;

	@Column
	public String created_by;

	@Column
	public String updated_by;

	@Column
	public String status;

	public BaseEntity(Date created_at, Date updated_at, String created_by, String updated_by, String status) {
		super();
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.created_by = created_by;
		this.updated_by = updated_by;
		this.status = status;
	}

	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BaseEntity [created_at=" + created_at + ", updated_at=" + updated_at + ", created_by=" + created_by
				+ ", updated_by=" + updated_by + ", status=" + status + "]";
	}

}
