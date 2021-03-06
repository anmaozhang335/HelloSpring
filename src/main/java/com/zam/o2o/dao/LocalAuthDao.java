package com.zam.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.zam.o2o.entity.LocalAuth;

public interface LocalAuthDao {

    /**
     * 通过账号和密码查询对应信息，用于登录
     * @param username
     * @param password
     * @return
     */
    LocalAuth queryLocalAuthByNameAndPwd(@Param("username") String username,@Param("password") String password); 
    /**
     * 通过用户id查询对应的localauth
     * @param userId
     * @return
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId) ;
    /**
     * 添加平台账号
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);
    /**
     * 通过userid，username,password更新密码
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @param lastEditTime
     * @return
     */
    int updateLocalAuth(@Param("userId") Long userId,@Param("username") String username,
        @Param("password") String password,@Param("newPassword") String newPassword,
        @Param("lastEditTime") Date lastEditTime);
    
}

