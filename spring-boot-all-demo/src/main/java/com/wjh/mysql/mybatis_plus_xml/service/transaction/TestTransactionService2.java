package com.wjh.mysql.mybatis_plus_xml.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wjh.mysql.mybatis_plus_xml.dao.UsersDao;
import com.wjh.mysql.mybatis_plus_xml.model.User;

@Service
public class TestTransactionService2 {
	
	@Autowired
	UsersDao usersDao;
	

	@Transactional(rollbackFor = Exception.class , propagation = Propagation.REQUIRES_NEW)
	public void insertUser2() {
		User user = new User();
		user.setNickName("222");
		usersDao.insert(user);
	}

}
