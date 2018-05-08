package com.lswq.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void selectUserByIdTest() {
        System.err.println(userService.selectUserById(1));
    }
    
}
