package com.crm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "status")
public class Status {
	@Id
	private String id;
	@NotBlank(message = "Tên không được bỏ trống!")
	@Length(min = 4, message = "Tên không được ít hơn 4 ký tự!")
	private String name;
	
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Task> tasks;
	public Status() {
		
	}
	public Status(String id, @NotBlank(message = "Tên không được bỏ trống!") @Length(min = 4, message = "Tên không được ít hơn 4 ký tự!") String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
