package com.lswq.dao;

import com.lswq.model.User;

public interface UserDao {
	User selectUserById(Integer userId);  
}
