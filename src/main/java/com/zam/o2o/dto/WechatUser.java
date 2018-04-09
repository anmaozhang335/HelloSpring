package com.zam.o2o.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信用户实体
 * 
 * @author azhang335
 *
 */
public class WechatUser implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2394903710745967518L;

    // 标识该公众号下面的该用户的唯一openId
    @JsonProperty("openId")
    private String openId;
    @JsonProperty("nickname")
    private String nickName;
    @JsonProperty("sex")
    private int sex;
    @JsonProperty("province")
    private String province;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("headimgurl")
    private String headimgurl;
    @JsonProperty("language")
    private String language;
    @JsonProperty("privilege")
    private String[] privilege;
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickname) {
        this.nickName = nickname;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String[] getPrivilege() {
        return privilege;
    }
    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }
    
    @Override
    public String toString() {
        return "openId:" + this.getOpenId() + ",nikename:" + this.getNickName();
    }
    
    

}
