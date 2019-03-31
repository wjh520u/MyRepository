package com.wjh.mysql.read_write_mybatis_plus_xml.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjh.common.bean.R;
import com.wjh.mysql.read_write_mybatis_plus_xml.dao.UsersDao;
import com.wjh.mysql.read_write_mybatis_plus_xml.model.User;

@Service
public class TestTransactionService {
	
	@Autowired
	TestTransactionService2 transactionService2;
	
	@Autowired
	UsersDao usersDao;
	
	public R trancation1(){
		
		return R.ok();
	}

	public void insertUser() {
		
		User user = new User();
		user.setNickName("111");
		usersDao.insert(user);
			transactionService2.insertUser2();
		int a = 4/0;
		
	}

}
