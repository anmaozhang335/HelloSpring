package com.zam.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.taskdefs.optional.depend.constantpool.StringCPInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zam.o2o.dto.ImageHolder;
import com.zam.o2o.dto.ProductExecution;
import com.zam.o2o.entity.Product;
import com.zam.o2o.entity.ProductCategory;
import com.zam.o2o.entity.Shop;
import com.zam.o2o.enums.ProductStateEnum;
import com.zam.o2o.exceptions.ProductOperationException;
import com.zam.o2o.service.ProductCategoryService;
import com.zam.o2o.service.ProductService;
import com.zam.o2o.util.CodeUtil;
import com.zam.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductManagementController {
    private static final String SUCCESS = "success";
    private static final String ERRORMSG = "errMsg";
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    // 支持上传商品详情图的最大数量
    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 验证码校验
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "输入了错误的验证码");
            return modelMap;
        }
        // 接收前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
            request.getSession().getServletContext());
        try {
            // 若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
            if (multipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request, thumbnail, productImgList);
            } else {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERRORMSG, "上传图片不能为空");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, e.toString());
            return modelMap;
        }
        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            // 尝试获取前端传过来的表单string流并将其转换成product实体类
            product = mapper.readValue(productStr, Product.class);

        } catch (Exception e) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, e.toString());
            return modelMap;
        }
        // 若product信息，缩略图以及详情图片列表非空，则开始进行商品添加操作
        if (product != null && thumbnail != null && !productImgList.isEmpty()) {
            try {
                // 从session中获取当前店铺的id并赋值给product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                // 执行添加操作
                ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put(SUCCESS, true);
                } else {
                    modelMap.put(SUCCESS, false);
                    modelMap.put(ERRORMSG, pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERRORMSG, e.toString());
                return modelMap;
            }
        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "请输入商品信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 是商品编辑时调用还是上下架操作时候调用
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        // 验证码判断
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "输入了错误的验证码");
            return modelMap;
        }
        // 接收钱带你参数的变量的初始化，包括商品，缩略图，详情图片列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
            request.getSession().getServletContext());
        try {
            if (commonsMultipartResolver.isMultipart(request)) {
                thumbnail = handleImage(request, thumbnail, productImgList);
            }
        } catch (Exception e) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, e.toString());
            return modelMap;
        }
        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, e.toString());
            return modelMap;
        }
        if (product != null) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                ProductExecution pExecution = productService.modifyProduct(product, thumbnail, productImgList);
                if (ProductStateEnum.SUCCESS.getState() == pExecution.getState()) {
                    modelMap.put(SUCCESS, true);
                } else {
                    modelMap.put(SUCCESS, false);
                    modelMap.put(ERRORMSG, pExecution.getStateInfo());
                    return modelMap;
                }
            } catch (RuntimeException e) {
                modelMap.put(SUCCESS, false);
                modelMap.put(ERRORMSG, e.toString());
                return modelMap;
            }
        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "请输入商品信息");

        }
        return modelMap;
    }

    @RequestMapping(value = "/getproductlistbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductListByShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if (pageIndex>-1&&pageSize>-1&&currentShop!=null&&currentShop.getShopId()!=null) {
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            String productName = HttpServletRequestUtil.getString(request, "productName");
            Product productCondition = compactProductCondition(currentShop.getShopId(), productCategoryId, productName);
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put(SUCCESS, true);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
        }else {
            modelMap.put(ERRORMSG, "empty pageIndex ot pageSize or currentShop");
            modelMap.put(SUCCESS, false);
        }
        return modelMap;
    }

    /**
     * 
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductById(@RequestParam Long productId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productId > -1) {
            Product product = productService.getProductById(productId);
            List<ProductCategory> productCategoryList = productCategoryService
                    .getProductCategoryList(product.getShop().getShopId());
            modelMap.put(SUCCESS, true);
            modelMap.put("product", product);
            modelMap.put("productCategoryList", productCategoryList);

        } else {
            modelMap.put(SUCCESS, false);
            modelMap.put(ERRORMSG, "empty productId");
        }
        return modelMap;

    }

    private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail, List<ImageHolder> productImgList)
        throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 取出缩略图并构件ImageHolder对象
        CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
        if (thumbnailFile != null) {
            thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
        }
        // 取出详情图列表并构建LIST<Imageholder>列表对象，最多支持六张图片上传
        for (int i = 0; i < IMAGEMAXCOUNT; i++) {
            CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
            if (productImgFile != null) {
                // 若取出的第i个详情图片的文件流不为空，则将其加入详情图列表
                ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
                    productImgFile.getInputStream());
                productImgList.add(productImg);
            } else {
                // 若取出的第i个详情图片文件流为空，则终止循环
                break;
            }
        }
        return thumbnail;
    }

    private Product compactProductCondition(long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if (productCategoryId != -1L) {
            ProductCategory pCategory = new ProductCategory();
            pCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(pCategory);
        }
        if (productName != null) {
            productCondition.setProductName(productName);
        }
        return productCondition;
    }
}
