package com.crm.dto;

import java.util.Date;

public class TaskDTO {
	String id;
	String name;
	Date endDate;
	String groupId;
	String groupName;
	String userId;
	String fullName;
	String statusId;
	String statusName;
	
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public TaskDTO(String id, String name, Date endDate, String groupId, String groupName, String userId, String fullName,
			String statusId, String statusName) {
		super();
		this.id = id;
		this.name = name;
		this.endDate = endDate;
		this.groupId = groupId;
		this.groupName = groupName;
		this.userId = userId;
		this.fullName = fullName;
		this.statusId = statusId;
		this.statusName = statusName;
	}
	
}
