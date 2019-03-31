package com.wjh.mysql.mybatis_plus_xml.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjh.common.bean.R;
import com.wjh.mysql.read_write_mybatis_plus_xml.model.User;

@Service
public class TestTransactionService {
	
	@Autowired
	TestTransactionService2 transactionService2;
	
	public R trancation1(){
		
		return R.ok();
	}

	@Transactional(rollbackFor = Exception.class)
	public void insertUser() {
		
		User user = new User();
		user.setNickName("111");
			transactionService2.insertUser2();
		int a = 4/0;
		
	}

}
