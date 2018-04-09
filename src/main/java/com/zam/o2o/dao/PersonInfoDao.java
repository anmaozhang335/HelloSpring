package com.zam.o2o.dao;

import com.zam.o2o.entity.PersonInfo;

public interface PersonInfoDao {

    /**
     * 
     * @param userId
     * @return
     */
    PersonInfo queryPersonInfoById(long userId);
    
    /**
     * 添加用户信息
     * @param personInfo
     * @return
     */
    int insertPersonInfo(PersonInfo personInfo);
}
