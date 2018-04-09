package com.zam.o2o.web.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zam.o2o.dto.UserAccessToken;
import com.zam.o2o.dto.WechatAuthExecution;
import com.zam.o2o.dto.WechatUser;
import com.zam.o2o.entity.PersonInfo;
import com.zam.o2o.entity.WechatAuth;
import com.zam.o2o.enums.WechatAuthStateEnum;
import com.zam.o2o.service.PersonInfoService;
import com.zam.o2o.service.WechatAuthService;
import com.zam.o2o.util.weixin.WechatUtil;

@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {

    private static Logger logger = LoggerFactory.getLogger(WechatLoginController.class);
    private static final String FRONTEND = "1";
    private static final String SHOPEND = "2";
    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private WechatAuthService wechatAuthService;

    @RequestMapping(value = "/logincheck", method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("weixin login get...");
        // 获取微信公众号传输过来的code，通过code可获取access_token,进而获取用户信息
        String code = request.getParameter("code");
        // 这个state可以用来传我们自定义的信息，方便程序调用，这里也可以不用
        String roleType = request.getParameter("state");
        logger.debug("weixin login code:" + code);
        WechatUser user = null;
        String openId = null;
        WechatAuth wechatAuth = null;
        if (code != null) {
            UserAccessToken token;
            // 通过code获取access_token
            token = WechatUtil.getUserAccessToken(code);
            logger.debug("weixin login token:" + token.toString());
            String accessToken = token.getAccessToken();
            openId = token.getOpenId();
            // 通过 access_token和openId获取用户昵称等信息
            user = WechatUtil.getUserInfo(accessToken, openId);
            logger.debug("weixin login user:" + user.toString());
            request.getSession().setAttribute("openId", openId);
            wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
        }
        // 若微信账号为空则需要注册微信账号，同时注册用户信息
        if (wechatAuth == null) {
            PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
            wechatAuth = new WechatAuth();
            wechatAuth.setOpenId(openId);
            if (FRONTEND.equals(roleType)) {
                personInfo.setUserType(1);
            } else {
                personInfo.setUserType(2);
            }
            wechatAuth.setPersonInfo(personInfo);
            WechatAuthExecution we = wechatAuthService.register(wechatAuth);
            if (we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
                return null;
            } else {
                personInfo = personInfoService.getPersonInfoById(wechatAuth.getPersonInfo().getUserId());
                request.getSession().setAttribute("user", personInfo);
            }
        }
        if (FRONTEND.equals(roleType)) {
            return "frontend/index";
        } else {
            return "shopadmin/shoplist";
        }
    }

}
