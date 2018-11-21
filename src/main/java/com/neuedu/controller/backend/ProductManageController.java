package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/product")
public class ProductManageController {

    @Autowired
    IProductService productService;

    /**
     * 新增OR更新产品
     * */
    @RequestMapping(value = "/save.do")
    public ServerResponse saveOrUpdate(HttpSession session, Product product) {

        return productService.saveOrUpdate(product);
    }

    /**
     * 产品上下架
     * */
    @RequestMapping(value = "/set_sale_status.do")
    public ServerResponse set_sale_status(HttpSession session, Integer productId, Integer status) {

        return productService.set_sale_status(productId, status);
    }

    /**
     *
     * */
    @RequestMapping(value = "/set_sale_status/{productId}/{status}")
    public ServerResponse set_sale_statusRestful(HttpSession session,
                                                 @PathVariable("productId") Integer productId,
                                                 @PathVariable("status") Integer status) {

        return productService.set_sale_status(productId, status);
    }

    /**
     * 查看商品详情
     * */
    @RequestMapping(value = "/detail.do")
    public ServerResponse detail(HttpSession session, Integer productId) {

        return productService.detail(productId);
    }

    /**
     *
     * */
    @RequestMapping(value = "/detail/productId/{productId}")
    public ServerResponse detailRestful(HttpSession session,
                                        @PathVariable("productId") Integer productId) {

        return productService.detail(productId);
    }

    /**
     * 查看商品列表
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize) {

        return productService.list(pageNum,pageSize);
    }

    /**
     *
     * */
    @RequestMapping(value = "/list/{pageNum}/{pageSize}")
    public ServerResponse listRestful(HttpSession session,
                               @PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {

        return productService.list(pageNum,pageSize);
    }

    /**
     * 产品搜索
     * */
    @RequestMapping(value = "/search.do")
    public ServerResponse search(HttpSession session,
                                 @RequestParam(value = "productId",required = false)Integer productId,
                                 @RequestParam(value = "productName",required = false)String productName,
                                 @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize) {

        return productService.search(productId,productName,pageNum,pageSize);
    }

    /**
     *
     * */
    @RequestMapping(value = "/search/productId/{productId}/{pageNum}/{pageSize}")
    public ServerResponse searchRestfulByProductId(HttpSession session,
                                 @PathVariable("productId") Integer productId,
                                 @PathVariable("pageNum") Integer pageNum,
                                 @PathVariable("pageSize") Integer pageSize) {

        return productService.search(productId,null,pageNum,pageSize);
    }
    @RequestMapping(value = "/search/productName/{productName}/{pageNum}/{pageSize}")
    public ServerResponse searchRestfulByProductName(HttpSession session,
                                          @PathVariable("productName") String productName,
                                          @PathVariable("pageNum") Integer pageNum,
                                          @PathVariable("pageSize") Integer pageSize) {

        return productService.search(null,productName,pageNum,pageSize);
    }

}
