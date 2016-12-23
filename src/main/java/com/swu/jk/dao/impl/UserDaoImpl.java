package com.swu.jk.dao.impl;

import org.springframework.stereotype.Repository;

import com.swu.jk.dao.UserDao;
import com.swu.jk.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	public UserDaoImpl() {
		super.setNs("com.swu.jk.mapper.UserMapper");
	}
	@Override
	public User login(User u) {
		User user = super.getSqlSession().selectOne(super.getNs() + ".login", u);
		return user;
	}
	
}
