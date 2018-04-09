package com.zam.o2o.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zam.o2o.dao.PersonInfoDao;
import com.zam.o2o.dao.WechatAuthDao;
import com.zam.o2o.dto.WechatAuthExecution;
import com.zam.o2o.entity.PersonInfo;
import com.zam.o2o.entity.WechatAuth;
import com.zam.o2o.enums.WechatAuthStateEnum;
import com.zam.o2o.exceptions.WechatAuthOperationException;
import com.zam.o2o.service.WechatAuthService;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    private static Logger logger = LoggerFactory.getLogger(WechatAuthServiceImpl.class);
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {

        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        if (wechatAuth == null || wechatAuth.getOpenId() == null) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            wechatAuth.setCreateTime(new Date());
            // 如果微信账号里夹带这用户信息并且用户id为空，则认为该用户第一次使用平台（且通过微信登录）
            // 则自动创建用户信息
            if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                    if (effectedNum <= 0) {
                        throw new WechatAuthOperationException("添加用户信息失败");
                    }
                } catch (Exception e) {
                    logger.error("insertPersonInfo error:" + e.toString());
                    throw new WechatAuthOperationException("insertPersonInfo error:" + e.toString());
                }
            }
            // 创建专属于本平台的微信账号
            int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
            if (effectedNum <= 0) {
                throw new WechatAuthOperationException("创建账号失败");
            } else {
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
            }
        } catch (Exception e) {
            logger.error("insertWechatAuth error:" + e.toString());
            throw new WechatAuthOperationException("insertWechatAuth error:" + e.toString());
        }
    }

}
