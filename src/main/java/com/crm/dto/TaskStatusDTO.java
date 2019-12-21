package com.crm.dto;

public class TaskStatusDTO {
	private long count;
	private String statusId;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public TaskStatusDTO(long count, String statusId) {
		super();
		this.count = count;
		this.statusId = statusId;
	}
	
}
