package com.wjh.mysql.multi_mybatis_plus_xml.dao.test2dao;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wjh.mysql.multi_mybatis_plus_xml.model.User;

@Component("UsersDao2")
public interface UsersDao extends BaseMapper<User>{

}
