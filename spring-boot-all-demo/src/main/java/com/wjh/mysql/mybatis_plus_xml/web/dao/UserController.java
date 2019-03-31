package com.wjh.mysql.mybatis_plus_xml.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.mysql.mybatis_plus_xml.model.User;
import com.wjh.mysql.mybatis_plus_xml.service.dao.UserService;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users=userService.getAll();
		return users;
	}
	
    @RequestMapping("/getUser")
    public User getUser(Long id) {
    	User user=userService.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public User save(User user) {
        userService.addUser(user);
        return user;
    }
    
    @RequestMapping(value="update")
    public User update(User user) {
        userService.updateUser(user);
        com.baomidou.mybatisplus.annotation.FieldStrategy lFieldStrategy;
        return user;
    }
    
    @RequestMapping(value="/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "ok";
    }
}