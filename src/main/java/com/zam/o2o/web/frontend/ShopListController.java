package com.zam.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zam.o2o.dto.ShopExecution;
import com.zam.o2o.entity.Area;
import com.zam.o2o.entity.Shop;
import com.zam.o2o.entity.ShopCategory;
import com.zam.o2o.service.AreaService;
import com.zam.o2o.service.ShopCategoryService;
import com.zam.o2o.service.ShopService;
import com.zam.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
    private static final String SUCCESS = "success";
    private static final String ERRORMSG = "errMsg";
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");
        List<ShopCategory> shopCategoryList = null;
        if (parentId != -1) {
            // 如果该 parentId 存在，则取出该一级分类下的二级分类列表
            try {
                ShopCategory shopCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCondition);
            } catch (Exception e) {

            }
        } else {
            // 如果parentid不存在，则取出所有一级ShopCategory列表
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERRORMSG, e.getMessage());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put(SUCCESS, true);
        } catch (Exception e) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/listshops", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        if (pageIndex > -1 && pageSize > -1) {
            // 尝试获取一级类别id
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            // 尝试获取二级类别id
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            // 尝试获取区域id
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");
            // 尝试获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            // 获得组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
            ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("count", se.getCount());
            modelMap.put(SUCCESS, true);
        }else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "empty pageIndex or pageSize!");
        }
        return modelMap;
    }

    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
        Shop shopCondition = new Shop();
        // 查询某个一级ShopCategory下面的所有二级ShopCategory下的商铺列表
        if (parentId != -1) {
            ShopCategory childShopCategory = new ShopCategory();
            ShopCategory parentShopCategory = new ShopCategory();
            parentShopCategory.setShopCategoryId(parentId);
            childShopCategory.setParent(parentShopCategory);
            shopCondition.setShopCategory(childShopCategory);
        }
        if (shopCategoryId != -1) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }

        if (areaId != -1) {
            // 查询位于某个区域下的商铺列表
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }
        if (shopName != null) {
            shopCondition.setShopName(shopName);
        }
        shopCondition.setEnableStatus(1);
        return shopCondition;
    }
}
