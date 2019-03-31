package com.wjh.mysql.read_write_mybatis_plus_xml.web.trancation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.common.bean.R;
import com.wjh.mysql.read_write_mybatis_plus_xml.service.transaction.TestTransactionService;
import com.wjh.mysql.read_write_mybatis_plus_xml.service.transaction.TestTransactionService2;

@RestController
public class TrancationController {
	
	@Autowired
	TestTransactionService testTransactionService;
	
	@Autowired
	TestTransactionService2 testTransactionService2;
	
	@RequestMapping("testTrancation")
	public R name() {
		
		
		testTransactionService.insertUser();
		
		return R.ok();
		
	}

}
