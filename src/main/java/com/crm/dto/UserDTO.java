package com.crm.dto;

public class UserDTO {
	String id;
	String email;
	String fullname;
	String address;
	String phone;
	String roleId;
	String roleName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserDTO(String id,String email, String fullname, String address, String phone, String roleId, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
}
