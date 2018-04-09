package com.zam.o2o.service;

import java.util.List;

import com.zam.o2o.entity.ShopCategory;

public interface ShopCategoryService {
    public static final String SCLISTKEY="shopcategorylist";
    /**
     * 根据查询条件获取shopcategory列表
     * @param shopCategoryCondition
     * @return
     */
   List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
