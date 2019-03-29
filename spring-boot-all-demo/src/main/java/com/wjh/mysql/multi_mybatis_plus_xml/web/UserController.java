package com.wjh.mysql.multi_mybatis_plus_xml.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.mysql.multi_mybatis_plus_xml.dao.UsersDao;
import com.wjh.mysql.multi_mybatis_plus_xml.mapper.one.User1Mapper;
import com.wjh.mysql.multi_mybatis_plus_xml.mapper.two.User2Mapper;
import com.wjh.mysql.multi_mybatis_plus_xml.model.User;

@RestController
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;
    
    @Autowired
    UsersDao usersDao;

	@Autowired
	private User2Mapper user2Mapper;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users=user1Mapper.getAll();
		return users;
	}
	
    @RequestMapping("/getUser")
    public User getUser(Long id) {
    	User user=user2Mapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public User save(User user) {
        usersDao.insert(user);
        return user;
    }
    
    @RequestMapping(value="update")
    public User update(User user) {
        user2Mapper.update(user);
        return user;
    }
    
    @RequestMapping(value="/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
        return "ok";
    }
    
    
}