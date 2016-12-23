package com.swu.jk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swu.jk.dao.UserDao;
import com.swu.jk.domain.User;
import com.swu.jk.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

}
