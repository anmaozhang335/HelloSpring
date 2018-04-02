package com.zam.o2o.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zam.o2o.dao.HeadLineDao;
import com.zam.o2o.dao.ShopCategoryDao;
import com.zam.o2o.entity.HeadLine;
import com.zam.o2o.entity.ShopCategory;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    private static final String SUCCESS="success";
    private static final String ERRORMSG="errMsg";
    
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private HeadLineDao headLineDao;
    /**
     * 初始化前端展示系统的主页信息，包括获取一级店铺类别列表以及头条信息
     * 
     * @return
     */
    @RequestMapping(value = "listmainpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listMainPageInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategories = new ArrayList<>();
        try {
            shopCategories = shopCategoryDao.queryShopCategory(null);
            modelMap.put("shopCategoryList", shopCategories);
        } catch (Exception e) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, e.getMessage());
            return modelMap;
        }
        List<HeadLine> headLines = new ArrayList<>();
        try {
            HeadLine headLine = new HeadLine();
            headLine.setEnableStatus(1);
            headLines = headLineDao.queryHeadLine(headLine);
            modelMap.put("headLineList", headLines);
        } catch (Exception e) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, e.getMessage());
            return modelMap;
        }
        modelMap.put(SUCCESS, true);
        return modelMap;

    }
}
