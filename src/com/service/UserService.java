package com.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.model.User;

public class UserService {
private EntityManager entityManager;
	public UserService(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
	this.entityManager=entityManager;
	}

	public User insertUser(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		// now return user object so that it can attached to manager
		Query query=entityManager.createNativeQuery("select * from user where username=?",User.class);
		query.setParameter(1,user.getUsername());
		User userObj=(User)query.getSingleResult();
		return userObj;
	}

	public boolean login(String username, String password, String role) {
		// TODO Auto-generated method stub
		Query query=entityManager.createNativeQuery("select * from user where username=? and password=? and role=?", User.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		query.setParameter(3, role);
		try {
		query.getSingleResult();
		return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public User getIdByUsername(String username) {
		// TODO Auto-generated method stub
		String sql="select * from user where username=?";
		Query query=entityManager.createNativeQuery(sql, User.class);
		query.setParameter(1, username);
		return (User) query.getSingleResult();
	}

}
