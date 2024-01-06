package com.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.exception.InvalidIdException;
import com.model.Department;
import com.model.Manager;

public class DepartmentService {
private EntityManager entityManager;
	public DepartmentService(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager=entityManager;
	}
	public void insert(Department department) {
		// TODO Auto-generated method stub
		entityManager.persist(department);
	}
//	public Department getDepartment(int id) throws InvalidIdException {
//		// TODO Auto-generated method stub
//		String sql="select * from department where id=?";
//		Query query=entityManager.createNativeQuery(sql,Department.class);
//		query.setParameter(1, id);
//		Department department=(Department)query.getSingleResult();
//		if(department==null) {
//			throw new InvalidIdException("Invalid id");
//		}
//		return department;
//		
//	}
	public Department getDepartment(int id) throws InvalidIdException {
		// TODO Auto-generated method stub
		Department department=entityManager.find(Department.class, id);
		if(department==null) {
			throw new InvalidIdException("Id is invalid");
		}
		return department;
	}

}
