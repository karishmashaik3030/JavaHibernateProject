package com.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.exception.InvalidIdException;
import com.model.Manager;

public class ManagerService {
private EntityManager entityManager;
	public ManagerService(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager=entityManager;
	}

	public void insertManager(Manager manager) {
		// TODO Auto-generated method stub
		entityManager.persist(manager);
	}

	public Manager getManager(int id) throws InvalidIdException {
		// TODO Auto-generated method stub
		Manager manager=entityManager.find(Manager.class, id);
		if(manager==null) {
			throw new InvalidIdException("Invalid Id");
		}
		return manager;
	}

	public int getManagerById(int userid) {
		// TODO Auto-generated method stub
		String sql="select * from manager where user_id=?";
		Query query = entityManager.createNativeQuery(sql, Manager.class);
		query.setParameter(1,userid);
		Manager managerObj=(Manager)query.getSingleResult();
		int id=managerObj.getId();
		return id;
	}

}
