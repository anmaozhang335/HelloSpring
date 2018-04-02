package com.zam.o2o.dto;

import java.util.List;

import com.zam.o2o.entity.ProductCategory;
import com.zam.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
    private int state;
    private String stateInfo;
    private List<ProductCategory> productCategoryList;
    public ProductCategoryExecution() {
        
    }
    //成功时调用的构造方法
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }
    //失败时调用的构造方法
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) 
    {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getStateInfo() {
        return stateInfo;
    }
    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }
    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
    
    
}
