package com.zam.o2o.service;

import java.util.List;

import com.zam.o2o.dto.ProductCategoryExecution;
import com.zam.o2o.entity.ProductCategory;
import com.zam.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
    /**
     * 获取指定id的商铺的商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(Long shopId);
    /**
     * 批量添加产品类别
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;
    
    /**
     * 将此类别下的商品的类别id设置为空，再删除掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
    
    
}
