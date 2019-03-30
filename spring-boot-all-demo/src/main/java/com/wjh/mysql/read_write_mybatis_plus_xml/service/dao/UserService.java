package com.wjh.mysql.read_write_mybatis_plus_xml.service.dao;

import java.util.List;

import com.wjh.mysql.read_write_mybatis_plus_xml.model.User;

public interface UserService {

	List<User> getAll();

	User getOne(Long id);

	User insertUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

}
