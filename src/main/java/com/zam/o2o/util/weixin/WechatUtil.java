package com.zam.o2o.util.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zam.o2o.dto.UserAccessToken;
import com.zam.o2o.dto.WechatUser;
import com.zam.o2o.entity.PersonInfo;

public class WechatUtil {

    private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    public static UserAccessToken getUserAccessToken(String code) {
        // 测试号信息里的appid
        String appId = "wxcd0963cc83000193";
        // 测试号信息里的appsecret
        String appsecret = "f4bd343a8bbef754f46c51dfe02c0955";
        // 根据传入的code，拼接出访问微信定义好的接口的url
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appsecret
            + "&code=" + code + "&grant_type=authorization_code";
        // 向相应url发送请求获取token json字符串
        String tokenStr = httpsRequest(url, "GET", null);
        logger.debug("userAccessToken:" + tokenStr);
        UserAccessToken token = new UserAccessToken();
        ObjectMapper mapper = new ObjectMapper();
        // 将json字符串转换成相应对象
        try {
            token = mapper.readValue(tokenStr, UserAccessToken.class);
        } catch (JsonParseException e) {
            logger.error("获取用户accessToken失败：" + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            logger.error("获取用户accessToken失败：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("获取用户accessToken失败：" + e.getMessage());
            e.printStackTrace();
        }
        if (token == null) {
            logger.error("获取用户accessToken失败：");
            return null;
        }
        return token;

    }

    /**
     * 获取WechatUser实体类
     * 
     * @param accessToken
     * @param openId
     * @return
     */
    public static WechatUser getUserInfo(String accessToken, String openId) {
        // 根据传入的accessToken以及openId拼接出访问微信定义的端口并获取用户信息的URL
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId
            + "&lang=zh_CN";
        // 访问该url获取用户信息json字符串
        String userStr = httpsRequest(url, "GET", null);
        logger.debug("user info:" + userStr);
        WechatUser user = new WechatUser();
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(userStr, WechatUser.class);
        } catch (JsonParseException e) {
            logger.error("获取用户信息失败：" + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            logger.error("获取用户信息失败：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("获取用户信息失败：" + e.getMessage());
            e.printStackTrace();
        }
        if (user == null) {
            logger.error("获取用户信息失败.");
            return null;
        }
        return user;
    }

    /**
     * 将WechatUser里的信息转换成Personinfo的信息并返回实体
     * 
     * @param user
     * @return
     */
    public static PersonInfo getPersonInfoFromRequest(WechatUser user) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName(user.getNickName());
        personInfo.setGender(user.getSex() + "");
        personInfo.setProfileImg(user.getHeadimgurl());
        personInfo.setEnableStatus(1);
        return personInfo;
    }

    /**
     * 发起https请求并获取结果
     * 
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式
     * @param outputStr
     *            提交的数据
     * @return
     */
    private static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpsURLConn = (HttpsURLConnection) url.openConnection();
            httpsURLConn.setSSLSocketFactory(ssf);
            httpsURLConn.setDoOutput(true);
            httpsURLConn.setDoInput(true);
            httpsURLConn.setUseCaches(false);
            httpsURLConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpsURLConn.connect();
            }
            // 当有数据需要提交时
            if (outputStr != null) {
                OutputStream outputStream = httpsURLConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符流
            InputStream inputStream = httpsURLConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String string = null;
            while ((string = bufferedReader.readLine()) != null) {
                buffer.append(string);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpsURLConn.disconnect();
            logger.debug("https buffer:" + buffer.toString());

        } catch (ConnectException ce) {
            logger.error("Weixin server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}", e);
        }
        return buffer.toString();
    }
}
