package com.forezp.finchley.client.feign.service.fallback;

import com.forezp.finchley.client.feign.service.SchedualServiceHi;
import com.forezp.finchley.common.entity.User;

//@Service
public class SchedualServiceHiFallback implements SchedualServiceHi {

	@Override
	public User sayHiFromClientOne( User user0,String name) {
		// TODO Auto-generated method stub
		User user = new User();
		user.id=-1;
		return user;
	}

}
