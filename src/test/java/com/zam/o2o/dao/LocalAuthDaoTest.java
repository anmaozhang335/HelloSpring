package com.zam.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.zam.o2o.BaseTest;
import com.zam.o2o.entity.LocalAuth;
import com.zam.o2o.entity.PersonInfo;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest{
    @Autowired
    private LocalAuthDao localAuthDao;
    private static final String username="testusername";
    private static final String password="testpassword";
    
    @Test
    @Ignore
    public void testAInsertLocalAuth() {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setPersonInfo(personInfo);
        localAuth.setCreateTime(new Date());
        int effectedNum = localAuthDao.insertLocalAuth(localAuth);
        assertEquals(1, effectedNum);
    }
    
    @Test
    public void testBQueryLocalByUserId() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        assertEquals("张安茂", localAuth.getPersonInfo().getName());
        
    }
    
    @Test
    public void testCQueryLocalAuthByNameAndPwd() {
        LocalAuth localAuth = localAuthDao.queryLocalAuthByNameAndPwd(username, password+"new");
        assertEquals("张安茂", localAuth.getPersonInfo().getName());
    }
    
    @Test
    @Ignore
    public void testUpdateLocalAuth() {
        Date now = new Date();
        int effectedNum = localAuthDao.updateLocalAuth(1L, username, password, password+"new", now);
        assertEquals(1, effectedNum);
    }
    

}
