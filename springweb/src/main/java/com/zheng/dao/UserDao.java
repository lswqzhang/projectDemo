package com.zheng.dao;

import com.zheng.model.User;

public interface UserDao {
	User selectUserById(Integer userId);  
}
