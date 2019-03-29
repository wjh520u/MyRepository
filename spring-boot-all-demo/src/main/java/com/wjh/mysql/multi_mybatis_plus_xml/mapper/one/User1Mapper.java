package com.wjh.mysql.multi_mybatis_xml.mapper.one;

import java.util.List;

import com.wjh.mysql.multi_mybatis_xml.model.User;

public interface User1Mapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}