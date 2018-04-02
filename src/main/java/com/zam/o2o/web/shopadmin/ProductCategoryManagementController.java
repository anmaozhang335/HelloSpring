package com.zam.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zam.o2o.dto.ProductCategoryExecution;
import com.zam.o2o.dto.Result;
import com.zam.o2o.entity.ProductCategory;
import com.zam.o2o.entity.Shop;
import com.zam.o2o.enums.ProductCategoryStateEnum;
import com.zam.o2o.exceptions.ProductCategoryOperationException;
import com.zam.o2o.service.ProductCategoryService;

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryManagementController {
    private static final String ERR_MSG = "errMsg";
    private static final String SUCCESS = "success";
    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        // Shop shop = new Shop();
        // shop.setShopId(1L);
        // request.getSession().setAttribute("currentShop", shop);
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, productCategoryList);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<>(false, ps.getStateInfo(), ps.getState());
        }
    }

    @RequestMapping(value = "/addproductcategories", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProductCategories(@RequestBody List<ProductCategory> productCategories,
        HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory productCategory : productCategories) {
            productCategory.setShopId(currentShop.getShopId());
        }
        if (productCategories != null && !productCategories.isEmpty()) {
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategories);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put(SUCCESS, true);
                } else {
                    modelMap.put(SUCCESS, false);
                    modelMap.put(ERR_MSG, pe.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {

                modelMap.put(SUCCESS, false);
                modelMap.put(ERR_MSG, e.toString());
            }
        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERR_MSG, "请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pce = productCategoryService.deleteProductCategory(productCategoryId,
                    currentShop.getShopId());
                if (pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put(SUCCESS, true);
                } else {
                    modelMap.put(ERR_MSG, pce.getStateInfo());
                    modelMap.put(SUCCESS, false);
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERR_MSG, e.getMessage());
                return modelMap;

            }
        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERR_MSG, "至少选中一个商品类别！");
        }
        return modelMap;
    }

}
