package com.zam.o2o.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zam.o2o.dto.LocalAuthExecution;
import com.zam.o2o.entity.LocalAuth;
import com.zam.o2o.entity.PersonInfo;
import com.zam.o2o.enums.LocalAuthStateEnum;
import com.zam.o2o.service.LocalAuthService;
import com.zam.o2o.util.CodeUtil;
import com.zam.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value = "local", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {
    private static final String SUCCESS = "success";
    private static final String ERRORMSG = "errMsg";

    @Autowired
    private LocalAuthService localAuthService;

    /**
     * 将用户信息与平台账号绑定
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> bindLocalAuth(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "错误的验证码");
            return modelMap;
        }
        // 获取输入的账号和密码
        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        // 从session中获取当前用户信息（用户一旦通过微信登录之后，便能获取到用户的信息）
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        // 非空判断，要求账号密码以及当前的用户session非空
        if (userName != null && password != null && user != null && user.getUserId() != null) {
            LocalAuth localAuth = new LocalAuth();
            localAuth.setUsername(userName);
            localAuth.setPassword(password);
            localAuth.setPersonInfo(user);
            // 绑定账号
            LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
            if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                modelMap.put(SUCCESS, true);
            } else {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERRORMSG, le.getStateInfo());
            }
        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "用户名和密码均不能为空");
        }
        return modelMap;
    }

    /**
     * 修改密码
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "输入了错误的验证码");
            return modelMap;
        }
        // 获取账号和原密码
        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        // 从session中获取当前用户信息
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        // 非空判断，要求账号新旧密码以及当前的用户session非空，且新旧密码不相同
        if (userName != null && password != null && newPassword != null && user != null && user.getUserId() != null
            && !password.equals(newPassword)) {
            try {
                // 查看原先账号，看看与输入的账号是否一致，不一致则认为是非法操作
                LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
                if (localAuth == null || !localAuth.getUsername().equals(userName)) {
                    modelMap.put(SUCCESS, false);
                    modelMap.put(ERRORMSG, "输入的账号非本次登录的账号");
                    return modelMap;
                }
                // 修改平台账号的用户密码
                LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), userName, password,
                    newPassword);
                if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                    modelMap.put(SUCCESS, true);
                } else {
                    modelMap.put(ERRORMSG, le.getStateInfo());
                    modelMap.put(SUCCESS, false);
                }

            } catch (Exception e) {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERRORMSG, e.toString());
                return modelMap;
            }
        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "请输入密码");
        }
        return modelMap;
    }

    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取是否需要进行验证码校验的标识符
        boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "输入了错误的验证码");
            return modelMap;
        }
        // 获取输入的账号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        if (userName != null && password != null) {
            LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
            if (localAuth != null) {
                // 若能获取到账号信息则登录成功
                modelMap.put(SUCCESS, true);
                // 同时在session里设置用户信息
                request.getSession().setAttribute("user", localAuth.getPersonInfo());
            } else {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERRORMSG, "用户名或密码错误");
            }

        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "用户名或密码均不能为空");
        }
        return modelMap;
    }
    
    /**
     * 当用户点击登出按钮的时候注销session
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logout(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //将用户session置为空
        request.getSession().setAttribute("user", null);
        modelMap.put(SUCCESS, true);
        return modelMap;
    }

}
