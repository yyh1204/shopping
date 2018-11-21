package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartContraller {

    @Autowired
    ICartService cartService;

    /**
     * 购物车添加商品
     * */
    @RequestMapping(value = "/add/{productId}/{count}")
    public ServerResponse add(HttpSession session,
                              @PathVariable("productId") Integer productId,
                              @PathVariable("count") Integer count){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.add(userInfo.getId(), productId, count);
    }

    /**
     * 购物车列表
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.list(userInfo.getId());
    }

    /**
     * 更新购物车某个商品数量
     * */
    @RequestMapping(value = "/update/{productId}/{count}")
    public ServerResponse update(HttpSession session,
                                 @PathVariable("productId") Integer productId,
                                 @PathVariable("count") Integer count){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.update(userInfo.getId(), productId, count);
    }

    /**
     * 移除购物车某些产品
     * */
    @RequestMapping(value = "/delete_product/productIds/{productIds}")
    public ServerResponse delete_product(HttpSession session,
                                         @PathVariable("productIds") String productIds){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.delete_product(userInfo.getId(), productIds);
    }

    /**
     * 购物车选中某个商品
     * */
    @RequestMapping(value = "/select/productId/{productId}")
    public ServerResponse select(HttpSession session,
                                 @PathVariable("productId") Integer productId){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.select(userInfo.getId(), productId, Const.CartCheckEnum.PRODUCT_CHECKED.getCode());
    }

    /**
     * 购物车取消选中某个商品
     * */
    @RequestMapping(value = "/un_select/productId/{productId}")
    public ServerResponse un_select(HttpSession session,
                                    @PathVariable("productId") Integer productId){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.select(userInfo.getId(), productId, Const.CartCheckEnum.PRODUCT_UNCHECKED.getCode());
    }

    /**
     * 全选
     * */
    @RequestMapping(value = "/select_all.do")
    public ServerResponse select_all(HttpSession session){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.select(userInfo.getId(), null, Const.CartCheckEnum.PRODUCT_CHECKED.getCode());
    }

    /**
     * 取消全选
     * */
    @RequestMapping(value = "/un_select_all.do")
    public ServerResponse un_select_all(HttpSession session){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.select(userInfo.getId(), null, Const.CartCheckEnum.PRODUCT_UNCHECKED.getCode());
    }

    /**
     * 查询在购物车里的产品数量
     * */
    @RequestMapping(value = "/get_cart_product_count.do")
    public ServerResponse get_cart_product_count(HttpSession session){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return  cartService.get_cart_product_count(userInfo.getId());
    }

}
