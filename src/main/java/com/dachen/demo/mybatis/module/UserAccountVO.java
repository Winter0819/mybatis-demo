package com.dachen.demo.mybatis.module;

import java.util.List;

public class UserAccountVO {
	private Long id;
	private Integer userId;
	private Long amount;
	private Long createTime;
	private Long updateTime;
	private List<UserAccountLog> logs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public List<UserAccountLog> getLogs() {
		return logs;
	}
	public void setLogs(List<UserAccountLog> logs) {
		this.logs = logs;
	}
}
