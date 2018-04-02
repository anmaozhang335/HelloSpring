package com.zam.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zam.o2o.dao.ProductDao;
import com.zam.o2o.dao.ProductImgDao;
import com.zam.o2o.dto.ImageHolder;
import com.zam.o2o.dto.ProductExecution;
import com.zam.o2o.entity.Product;
import com.zam.o2o.entity.ProductImg;
import com.zam.o2o.enums.ProductStateEnum;
import com.zam.o2o.exceptions.ProductOperationException;
import com.zam.o2o.service.ProductService;
import com.zam.o2o.util.ImageUtil;
import com.zam.o2o.util.PageCalculator;
import com.zam.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    // 1.处理缩略图，获取缩略图相对路径并赋值给product.
    // 2.往tb_product写入商品信息，获取productId.
    // 3.结合productId批量处理商品详情图
    // 4.将商品详情图列表批量插入tb_product_img中.
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
        throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // 给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // 默认为上架的状态
            product.setEnableStatus(1);
            // 若商品缩略图不为空则添加
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                // 创建商品信息
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败：" + e.getMessage());
            }
            // 若商品详情图不为空则添加
            if (productImgList != null && !productImgList.isEmpty()) {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            // 传参为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * 批量添加图片
     * 
     * @param product
     * @param productImgList
     */

    private void addProductImgList(Product product, List<ImageHolder> productImgList) {
        // 获取图片存储路径，这里直接存放到相应店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> newProductImgList = new ArrayList<>();
        // 遍历图片一次去处理，并添加进productImg实体类里
        for (ImageHolder imageHolder : productImgList) {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            newProductImgList.add(productImg);
        }
        // 如果确实是有图片需要添加的，就执行批量添加操作
        if (!newProductImgList.isEmpty()) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(newProductImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
            }
        }

    }

    /**
     * 添加缩略图
     * 
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);

    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductByProductId(productId);
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
        List<ImageHolder> productImgHolderList) throws ProductOperationException {
        if (product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null) {
            //给商品设置默认属性
            product.setLastEditTime(new Date());
            //若商品缩略图不为空且原有缩略图不为空，则删除原有图并添加
            if (thumbnail!=null) {
                Product tempProduct = productDao.queryProductByProductId(product.getProductId());
                if (tempProduct.getImgAddr()!=null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            if (productImgHolderList!=null&&!productImgHolderList.isEmpty()) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgHolderList);
            }
            try {
                //更新商品信息
                int effectetNum = productDao.updateProduct(product);
                if (effectetNum<=0) {
                    throw new ProductOperationException("更新商品信息失败！");
                }else {
                    return new ProductExecution(ProductStateEnum.SUCCESS,product);
                }
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败:" + e.toString());
            }
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }
    
    private void deleteProductImgList(long productId) {
        //根据productId获取原来的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        //删除数据库里原有图片的信息
        productImgDao.deleteProductImgByProductId(productId);
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }
    
    

}
