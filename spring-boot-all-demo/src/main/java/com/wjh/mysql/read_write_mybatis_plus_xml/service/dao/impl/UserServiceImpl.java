package com.wjh.mysql.read_write_mybatis_plus_xml.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.mysql.read_write_mybatis_plus_xml.dao.UsersDao;
import com.wjh.mysql.read_write_mybatis_plus_xml.model.User;
import com.wjh.mysql.read_write_mybatis_plus_xml.service.dao.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UsersDao, User> implements UserService {
	
	@Autowired
    UsersDao usersDao;

	@Override
	public List<User> getAll() {
		List<User> selectList = this.baseMapper.selectList(null);
		return selectList;
	}

	@Override
	public User getOne(Long id) {
		return this.baseMapper.selectById(id);
	}

	@Override
	public User insertUser(User user) {
		this.baseMapper.insert(user);
		int y = 4/0;
		return user;
	}

	@Override
	public void updateUser(User user) {
		this.baseMapper.updateById(user);
	}

	@Override
	public void deleteUserById(Long id) {
		this.baseMapper.deleteById(id);
	}

}
