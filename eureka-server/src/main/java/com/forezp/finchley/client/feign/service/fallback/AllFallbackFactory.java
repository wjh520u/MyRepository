package com.forezp.finchley.client.feign.service.fallback;

import com.forezp.finchley.client.feign.service.SchedualServiceHi;
import com.forezp.finchley.common.entity.User;

import feign.hystrix.FallbackFactory;

//@Component
public class AllFallbackFactory implements FallbackFactory<SchedualServiceHi> {

	@Override
	public SchedualServiceHi create(Throwable cause) {
		// TODO Auto-generated method stub
		return new SchedualServiceHi() {
			
			@Override
			public User sayHiFromClientOne( User user0,String name) {
				User user = new User();
				user.id=-1;
				return user;
			}
		};
	}

}
