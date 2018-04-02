package com.zam.o2o.service;

import java.io.InputStream;

import com.zam.o2o.dto.ImageHolder;
import com.zam.o2o.dto.ShopExecution;
import com.zam.o2o.entity.Shop;
import com.zam.o2o.exceptions.ShopOperationException;

public interface ShopService {
    /**
     * 
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
    /**
     * 根据商铺的id获取商铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);
    /**
     * 更新店铺信息，包括对图片的处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, ImageHolder shopImgInputStream) throws ShopOperationException;
    /**
     * 新增店铺信息，包括店铺缩略图
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, ImageHolder shopImgInputStream) throws ShopOperationException;
}
