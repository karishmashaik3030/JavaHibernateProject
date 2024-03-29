package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
private String empcode;
@OneToOne
private User user;
public int getId() {
	return id;
}
public String getName() {
	return name;
}
public String getEmpcode() {
	return empcode;
}
public User getUser() {
	return user;
}
public void setId(int id) {
	this.id = id;
}
public void setName(String name) {
	this.name = name;
}
public void setEmpcode(String empcode) {
	this.empcode = empcode;
}
public void setUser(User user) {
	this.user = user;
}
@Override
public String toString() {
	return "Manager [id=" + id + ", name=" + name + ", empcode=" + empcode + ", user=" + user + "]";
}

}
