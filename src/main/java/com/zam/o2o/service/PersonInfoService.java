package com.zam.o2o.service;

import com.zam.o2o.entity.PersonInfo;

public interface PersonInfoService {

    /**
     * 根据用户id获取personinfo信息
     * @param userId
     * @return
     */
    PersonInfo getPersonInfoById(Long userId);
}
