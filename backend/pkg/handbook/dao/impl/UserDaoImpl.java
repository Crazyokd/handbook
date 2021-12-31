package pkg.handbook.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import pkg.handbook.dao.UserDao;
import pkg.handbook.domain.User;
import pkg.handbook.utils.JdbcUtils;

public class UserDaoImpl implements UserDao{
	public void add(String username, String password){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(username,password) values(?,?)";
			Object params[] = {username, password};
			runner.update(sql, params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public User search(String username){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=?";
			return runner.query(JdbcUtils.getConnection(), sql, new BeanHandler<>(User.class), username);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public User search(String username, String password){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object params[] = {username, password};
			return runner.query(JdbcUtils.getConnection(), sql, new BeanHandler<>(User.class), params);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}    
}
