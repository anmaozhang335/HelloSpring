package com.zam.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zam.o2o.BaseTest;
import com.zam.o2o.entity.Area;
import com.zam.o2o.entity.PersonInfo;
import com.zam.o2o.entity.Shop;
import com.zam.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
   @Autowired
   private ShopDao shopDao;
   @Test
   @Ignore
   public void TestInsertShop() {
	   Shop shop = new Shop();
	   PersonInfo owner = new PersonInfo();
	   owner.setUserId(1L);
	   ShopCategory shopCategory = new ShopCategory();
	   shopCategory.setShopCategoryId(1L);
	   Area area = new Area();
	   area.setAreaId(2);
	   shop.setArea(area);
	   shop.setOwner(owner);
	   shop.setShopCategory(shopCategory);
	   shop.setShopName("测试的店铺");
	   shop.setShopAddr("test");
	   shop.setShopDesc("Test");
	   shop.setShopImg("test");
	   shop.setEnableStatus(1);
	   shop.setAdvice("审核中");
	   shop.setPhone("1234");
	   shop.setCreateTime(new Date());
	   int effectedNum = shopDao.insertShop(shop);
	   assertEquals(1, effectedNum);
	   
   }
   @Test
   @Ignore
   public void TestUpdateShop() {
	   Shop shop = new Shop();
	   shop.setShopId(1L);
	   shop.setShopAddr("测试的地址");
	   shop.setShopDesc("测试的描述");
	   shop.setLastEditTime(new Date());
	   int effectedNum = shopDao.updateShop(shop);
	   assertEquals(1, effectedNum);
	   
   }
   @Test
   @Ignore
   public void testQueryByShopId() {
       long id = 1;
       Shop shop = shopDao.queryShopById(id);
       System.out.println(shop.getArea().getAreaName());
       System.out.println(shop.getArea().getAreaId());
   }
   
   @Test
   public void testQueryShopList() {
       Shop shopCondition = new Shop();
       PersonInfo owner = new PersonInfo();
      
       owner.setUserId(1L);
       shopCondition.setOwner(owner);
       List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
       int count = shopDao.queryShopCount(shopCondition);
       System.out.println("店铺的数量是："+ shopList.size());
       System.out.println("店铺的总数是："+ count);
       ShopCategory shopCategory   = new ShopCategory();
       shopCategory.setShopCategoryId(2L);
       shopCondition.setShopCategory(shopCategory);
       shopList = shopDao.queryShopList(shopCondition, 0, 5);
       System.out.println("xin"+shopList.size());
       count = shopDao.queryShopCount(shopCondition);
       System.out.println("xin"+count);
       
      
   }
}
