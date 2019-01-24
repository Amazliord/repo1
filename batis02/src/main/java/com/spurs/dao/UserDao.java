package com.spurs.dao;

import com.spurs.domain.Account;
import com.spurs.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    List<User> findOne(List list);

    List<User> findById(List list);

    List<Account> findAccount(List list);

}
