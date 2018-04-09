package com.zam.o2o.dao;

import com.zam.o2o.entity.WechatAuth;

public interface WechatAuthDao {

    /**
     * 通过openid查询对应平台的微信账号
     * 
     * @param openId
     * @return
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /**
     * 添加对应本平台的微信账号
     * 
     * @param wechatAuth
     * @return
     */
    int insertWechatAuth(WechatAuth wechatAuth);

}
