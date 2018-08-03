package com.dachen.demo.mybatis.module;

public class UserAccount {
	private Long id;
	private Integer userId;
	private Long amount;
	private Long createTime;
	private Long updateTime;
	public UserAccount() {
		super();
	}
	public UserAccount(Long id, Integer userId, Long amount, Long createTime, Long updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
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
}
