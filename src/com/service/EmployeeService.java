package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.exception.InvalidIdException;
import com.model.Employee;

public class EmployeeService {
private EntityManager entityManager;
	public EmployeeService(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager=entityManager;
	}
	public void insertEmployee(Employee employee) {
		// TODO Auto-generated method stub
		entityManager.persist(employee);
	}
	public List<Employee> getAllEmployees(int managerid) {
		// TODO Auto-generated method stub
		String sql="select * from employee where manager_id=?";
		Query query=entityManager.createNativeQuery(sql, Employee.class);
		query.setParameter(1, managerid);
		return query.getResultList();
	}
	public Employee getEmployeeById(int id) throws InvalidIdException {
		// TODO Auto-generated method stub
		Employee employee= entityManager.find(Employee.class, id);
		if(employee==null) {
			throw new InvalidIdException("Employee details not found");
		}
		return employee;
	}
	public void updateEmployee(Employee updateEmployee) {
		// TODO Auto-generated method stub
		entityManager.merge(updateEmployee);
//		entityManager.flush();
	}
	public void deleteById(Employee employee) {
		// TODO Auto-generated method stub
//		Query query= entityManager.createNativeQuery("delete from employee where id=?");
//		query.setParameter(1, id);
		entityManager.remove(employee);
	}

}
