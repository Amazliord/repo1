package com.spurs;

import com.spurs.dao.RoleDao;
import com.spurs.dao.UserDao;
import com.spurs.domain.Account;
import com.spurs.domain.User;
import com.spurs.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class testUser {
    static InputStream in = null;
    static SqlSessionFactoryBuilder builder = null;
    static SqlSessionFactory build = null;
    static UserDao mapper = null;
    static SqlSession sqlSession = null;
    @Before
    public void init(){
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        builder = new SqlSessionFactoryBuilder();
        build = builder.build(in);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(UserDao.class);
    }

    @Test
    public void findAll()throws Exception{

        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void findOne() throws Exception{
        ArrayList<Integer> list = new ArrayList<Integer>();
        /*list.add(2);
        list.add(3);*/
        List<User> one = mapper.findOne(list);

        for (User user : one) {
            System.out.println(user);
        }
        sqlSession.close();
        in.close();
    }

    @Test
    public void findById(){
        List<User> byId = mapper.findById(new ArrayList());
        for (User user : byId) {
            System.out.println("=============");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void findAccount(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1000);
        list.add(1001);
        List<Account> account = mapper.findAccount(list);
        for (Account account1 : account) {
            System.out.println(account1);
        }
    }

    @Test
    public void findAllRole(){
        RoleDao mapper = sqlSession.getMapper(RoleDao.class);
        List<Role> allRole = mapper.findAllRole();
        for (Role role : allRole) {
            System.out.println("============");
            System.out.println(role);
            //System.out.println(role.getUsers());
        }
    }

    @After
    public void destroy(){
        sqlSession.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
