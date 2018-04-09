package com.zam.o2o.service;

import com.zam.o2o.dto.WechatAuthExecution;
import com.zam.o2o.entity.WechatAuth;
import com.zam.o2o.exceptions.WechatAuthOperationException;

public interface WechatAuthService {

    /**
     * 通过openid查找平台对应的微信账号
     * @param openId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String openId);
    
      /**
       * 注册本平台的微信账号
       * @param wechatAuth
       * @return
       * @throws WechatAuthOperationException
       */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
    
}
