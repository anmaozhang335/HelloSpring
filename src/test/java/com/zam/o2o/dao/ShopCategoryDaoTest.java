package com.zam.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zam.o2o.BaseTest;
import com.zam.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryArea() {
        List<ShopCategory> shopCategoriyList = shopCategoryDao.queryShopCategory(new ShopCategory());
        ShopCategory testShopCategory = new ShopCategory();
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(1L);
        testShopCategory.setParent(parent);
        shopCategoriyList = shopCategoryDao.queryShopCategory(testShopCategory);
        assertEquals(1, shopCategoriyList.size());
        System.out.println(shopCategoriyList.get(0).getShopCategoryName());
    }
    
    @Test
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(null);
        assertEquals(1, shopCategories.size());
    }

}
