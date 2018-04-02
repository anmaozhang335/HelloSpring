package com.zam.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zam.o2o.entity.Product;

public interface ProductDao {
    /**
     * 插入商品
     * 
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 根据指定的productid查询唯一的商品信息
     * 
     * @param productId
     * @return
     */
    Product queryProductByProductId(long productId);

    /**
     * 根据传入的product修改商品信息
     * 
     * @param product
     * @return
     */
    int updateProduct(Product product);
    /**
     * 查询商品列表并分页，可以输入的条件有：商品名，商品状态，店铺id， 商品类别
     * @param productContidion
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productContidion, @Param("rowIndex") int rowIndex,
        @Param("pageSize") int pageSize);
    /**
     * 根据条件返回商品的总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);
    
    /**
     * 删除商品类别之前，将商品类别id置空
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);
    
}
