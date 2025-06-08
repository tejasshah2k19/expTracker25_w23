package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate stmt;

	public UserBean findByEmail(String email) {

		try {
			UserBean user = stmt.queryForObject("select * from users where email  = ? ",
					new BeanPropertyRowMapper<>(UserBean.class), new Object[] { email });
		
			return user;
		} catch (Exception e) {

		}
		return null;
	}

}
