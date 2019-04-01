package com.wjh.mysql.jdbc_template.entity;


import java.io.Serializable;
import java.util.Date;

import com.wjh.mysql.jdbc_template.annotation.Column;
import com.wjh.mysql.jdbc_template.annotation.Pk;
import com.wjh.mysql.jdbc_template.annotation.Table;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @package: com.xkcoding.orm.jdbctemplate.entity
 * @description: 用户实体类
 * @author: yangkai.shen
 * @date: Created in 2018/10/15 10:45 AM
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Table(name = "orm_user")
public class User implements Serializable {
	/**
	 * 主键
	 */
	@Pk
	private Long id;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 加密后的密码
	 */
	private String password;

	/**
	 * 加密使用的盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号码
	 */
	@Column(name = "phone_number")
	private String phoneNumber;

	/**
	 * 状态，-1：逻辑删除，0：禁用，1：启用
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 上次登录时间
	 */
	@Column(name = "last_login_time")
	private Date lastLoginTime;

	/**
	 * 上次更新时间
	 */
	@Column(name = "last_update_time")
	private Date lastUpdateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
