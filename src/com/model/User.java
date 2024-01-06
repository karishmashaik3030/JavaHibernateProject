package com.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.enums.RoleType;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String username;
private String password;
@Enumerated(EnumType.STRING)
private RoleType role;
public int getId() {
	return id;
}
public String getUsername() {
	return username;
}
public String getPassword() {
	return password;
}
public RoleType getRole() {
	return role;
}
public void setId(int id) {
	this.id = id;
}
public void setUsername(String username) {
	this.username = username;
}
public void setPassword(String password) {
	this.password = password;
}
public void setRole(RoleType role) {
	this.role = role;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
}

}
