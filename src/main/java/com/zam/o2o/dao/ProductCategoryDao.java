package com.zam.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zam.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
    /**
     * 通过shopId查询店铺的商品类别
     * @param shopId
     * @return
     */
   List<ProductCategory> queryProductCategoryList(long shopId);
   
   /**
    * 批量添加商品类别
    * @param productCategoryList
    * @return
    */
   int batchInsertProductCategory(List<ProductCategory> productCategoryList);
   /**
    * 根据指定的条件删除商品分类
    * @param productCategoryId
    * @param shopId
    * @return
    */
   int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
}
