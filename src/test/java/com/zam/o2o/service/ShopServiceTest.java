package com.zam.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zam.o2o.BaseTest;
import com.zam.o2o.dto.ImageHolder;
import com.zam.o2o.dto.ShopExecution;
import com.zam.o2o.entity.Area;
import com.zam.o2o.entity.PersonInfo;
import com.zam.o2o.entity.Shop;
import com.zam.o2o.entity.ShopCategory;
import com.zam.o2o.enums.ShopStateEnum;
import com.zam.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{
   @Autowired
   private ShopService shopService;
   @Test
   @Ignore
   public void testAddShop() throws FileNotFoundException {
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
	   shop.setShopName("测试的店铺3");
	   shop.setShopAddr("test1");
	   shop.setShopDesc("Test1");
	   shop.setEnableStatus(1);
	   shop.setAdvice("审核中");
	   shop.setPhone("1234");
	   shop.setCreateTime(new Date());
	   shop.setEnableStatus(ShopStateEnum.CHECK.getState());
	   File imgFile = new File("/Users/azhang335/Desktop/IMG_2138.JPG");
	   InputStream inputStream = new FileInputStream(imgFile);
	   ImageHolder imageHolder = new ImageHolder(imgFile.getName(), inputStream);
	   ShopExecution sExecution = shopService.addShop(shop, imageHolder);
	   assertEquals(ShopStateEnum.CHECK.getState(), sExecution.getState());
   }
   @Test
   @Ignore
   public void testModifyShop() throws ShopOperationException,FileNotFoundException
   {
       Shop shop = new Shop();
       shop.setShopId(1L);
       shop.setShopName("修改后的店铺名称");
       File imgFile = new File("/Users/azhang335/Desktop/Picture1.png");
       InputStream  is = new FileInputStream(imgFile);
       ImageHolder imageHolder = new ImageHolder("Picture1.png", is);
       ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
       System.out.println(shopExecution.getShop().getShopImg());
   }
   
   @Test
   public void testGetShopList() {
       Shop shopCondition = new Shop();
       ShopCategory sc = new ShopCategory();
       sc.setShopCategoryId(2L);
       shopCondition.setShopCategory(sc);
       ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
       System.out.println("店铺数量为："+ se.getShopList().size());
       System.out.println("店铺的总数为："+ se.getCount());
   }
}
