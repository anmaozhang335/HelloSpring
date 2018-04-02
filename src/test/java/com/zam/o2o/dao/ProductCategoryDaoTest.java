package com.zam.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.zam.o2o.BaseTest;
import com.zam.o2o.entity.ProductCategory;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    
    @Test
    public void testBQueryProductCategoryList() {
        long shopId=1L;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("商铺1的商品种类个数为："+ productCategories.size());
    }
    
    @Test
    public void testABatchInsertProductCategory() {
        ProductCategory p1 = new ProductCategory();
        ProductCategory p2 = new ProductCategory();
        p1.setProductCategoryName("商品类别4");
        p1.setPriority(11);
        p1.setCreateTime(new Date());
        p1.setShopId(1L);
        p2.setProductCategoryName("商品类别5");
        p2.setPriority(15);
        p2.setCreateTime(new Date());
        p2.setShopId(1L);
        List<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(p1);
        productCategories.add(p2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategories);
        assertEquals(2, effectedNum);
    }
    
    @Test
    public void testCDeleteProductCategory() {
        long shopId = 1L;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory pc : productCategories) {
            if ("商品类别4".equals(pc.getProductCategoryName())||"商品类别5".equals(pc.getProductCategoryName())) {
                int effecteNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
                assertEquals(1, effecteNum);
            }
        }
    }

}
