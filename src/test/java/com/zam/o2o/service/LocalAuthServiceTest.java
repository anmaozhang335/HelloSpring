package com.zam.o2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.zam.o2o.BaseTest;
import com.zam.o2o.dto.LocalAuthExecution;
import com.zam.o2o.entity.LocalAuth;
import com.zam.o2o.entity.PersonInfo;
import com.zam.o2o.enums.LocalAuthStateEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest {

    @Autowired
    private LocalAuthService localAuthService;

    @Test
    public void testABindLocalAuth() {
        String password = "testpassword";
        String username = "testusername";
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setPassword(password);
        localAuth.setUsername(username);
        localAuth.setPersonInfo(personInfo);
        LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
        assertEquals(LocalAuthStateEnum.SUCCESS.getState(), lae.getState());
        localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
        System.out.println(localAuth.getPersonInfo().getName());
        System.out.println(localAuth.getPassword());

    }
    
    @Test
    public void testBModifyLocalAuth() {
        long userId = 1;
        String password = "testpassword";
        String username = "testusername";
        String newPassword = "newtestpassword";
        LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId, username, password, newPassword);
        assertEquals(LocalAuthStateEnum.SUCCESS.getState(), lae.getState());
        LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, newPassword);
        System.out.println(localAuth.getPersonInfo().getName());
    }
}
