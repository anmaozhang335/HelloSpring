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
import com.zam.o2o.entity.ProductImg;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest{

    @Autowired
    private ProductImgDao productImgDao;
    
    @Test
    public void testABatchInsertProductImgList() {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("详情图片1");
        productImg1.setPriority(1);
        productImg1.setProductId(1L);
        productImg1.setCreateTime(new Date());
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(2);
        productImg2.setProductId(1L);
        productImg2.setCreateTime(new Date());
        List<ProductImg> productImgs = new ArrayList<>();
        productImgs.add(productImg1);
        productImgs.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgs);
        assertEquals(2, effectedNum);
    }
    @Test
    public void testCDeleteProductImgByProductId() {
        long productId=1L;
        int effectedNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2, effectedNum);
    }
}
