package com.simba.permission.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.simba.framework.util.jdbc.Jdbc;
import com.simba.framework.util.jdbc.Pager;
import com.simba.framework.util.jdbc.StatementParameter;
import com.simba.permission.dao.UserDao;
import com.simba.permission.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

  @Autowired
  private Jdbc jdbc;

  private static final String table = "systemUser";

  @Override
  public void add(User user) {
    String sql = "insert into " + table + "(account,name,pwd) values(?,?,?)";
    StatementParameter param = new StatementParameter();
    param.setString(user.getAccount());
    param.setString(user.getName());
    param.setString(user.getPwd());
    jdbc.updateForBoolean(sql, param);
  }

  @Override
  @CacheEvict(value = "default", key = "\"user_\".concat(#user.getAccount())")
  public void update(User user) {
    String sql = "update " + table + " set name =? ,pwd=?  where account = ?  ";
    StatementParameter param = new StatementParameter();
    param.setString(user.getName());
    param.setString(user.getPwd());
    param.setString(user.getAccount());
    jdbc.updateForBoolean(sql, param);
  }

  @Override
  @CacheEvict(value = "default", key = "\"user_\".concat(#account)")
  public void delete(String account) {
    String sql = "delete from " + table + " where account = ? ";
    jdbc.updateForBoolean(sql, account);
  }

  @Override
  @CacheEvict(value = "default", key = "\"user_\".concat(#account)")
  public void updateName(String account, String name) {
    String sql = "update " + table + " set name =  ? where account = ? ";
    jdbc.updateForBoolean(sql, name, account);
  }

  @Override
  public void updatePwd(String account, String pwd) {
    String sql = "update " + table + " set pwd =  ? where account = ? ";
    jdbc.updateForBoolean(sql, pwd, account);
  }

  @Override
  @Cacheable(value = "default", key = "\"user_\".concat(#account)")
  public User get(String account) {
    String sql = "select * from " + table + " where account = ? ";
    return jdbc.query(sql, User.class, account);
  }

  @Override
  public List<User> page(Pager page) {
    String sql = "select * from " + table;
    return jdbc.queryForPage(sql, User.class, page);
  }

  @Override
  public boolean checkUser(String account, String pwd) {
    String sql = "select count(*) from " + table + " where account = ? and pwd = ? ";
    return jdbc.queryForInt(sql, account, pwd) > 0;
  }

  @Override
  public List<User> listAll() {
    String sql = "select * from " + table;
    return jdbc.queryForList(sql, User.class);
  }

}
