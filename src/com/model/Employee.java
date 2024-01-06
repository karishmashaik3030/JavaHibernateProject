package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
private String contact;
private String designation;
private double salary;
@OneToOne
private User user;
@ManyToOne
private Manager manager;
@ManyToOne
private Department department;
public int getId() {
	return id;
}
public String getName() {
	return name;
}
public String getContact() {
	return contact;
}
public String getDesignation() {
	return designation;
}
public double getSalary() {
	return salary;
}
public User getUser() {
	return user;
}
public Manager getManager() {
	return manager;
}
public Department getDepartment() {
	return department;
}
public void setId(int id) {
	this.id = id;
}
public void setName(String name) {
	this.name = name;
}
public void setContact(String contact) {
	this.contact = contact;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public void setUser(User user) {
	this.user = user;
}
public void setManager(Manager manager) {
	this.manager = manager;
}
public void setDepartment(Department department) {
	this.department = department;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", contact=" + contact + ", designation=" + designation
			+ ", salary=" + salary + ", user=" + user + ", manager=" + manager + ", department=" + department + "]";
}

}
