package com.crm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "task")
public class Task {
	@Id
	private String id;
	
	private String name;
	
	@Column(name = "end_Date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@Column(name="group_id")
	private String groupId;
	
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;
	
	@Column(name = "user_id")
	private String userId;
	
	@ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private User user;
	
	@Column(name = "status_id")
	private String statusId;
	
	@ManyToOne
	@JoinColumn(name="status_id", insertable = false, updatable = false)
	private Status status;
	
	public Task() {
		
	}
	public Task(String id, String name, Date endDate, String groupId, String userId, String statusId) {
		super();
		this.id = id;
		this.name = name;
		this.endDate = endDate;
		this.groupId = groupId;
		this.userId = userId;
		this.statusId = statusId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
