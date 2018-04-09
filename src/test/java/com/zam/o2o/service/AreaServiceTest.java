package com.zam.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zam.o2o.BaseTest;
import com.zam.o2o.entity.Area;

public class AreaServiceTest extends BaseTest{
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;
    @Test
    public void testGetAreaList() {
    	   List<Area> areaList =  areaService.getAreaList();
    	   assertEquals("卢湾校区",areaList.get(0).getAreaName());
    	   cacheService.removeFromCache(areaService.AREALISTKEY);
    	   areaService.getAreaList();
    }
}
