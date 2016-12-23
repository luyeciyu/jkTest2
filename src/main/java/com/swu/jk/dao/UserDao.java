package com.swu.jk.dao;

import com.swu.jk.domain.User;

public interface UserDao extends BaseDao<User>{
	public User login(User u);
}
