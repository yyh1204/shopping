package com.neuedu.controller.backend;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/category")
public class CategoryManageController {

    @Autowired
    ICategoryService categoryService;

    /**
     * 获取品类子节点(平级)
     * */
    @RequestMapping(value = "/get_category/categoryId/{categoryId}")
    public ServerResponse get_category(HttpSession session,
                                       @PathVariable("categoryId") Integer categoryId){

        return  categoryService.get_category(categoryId);
    }

    /**
     * 增加节点
     * */
    @RequestMapping(value = "/add_category/{parentId}/{categoryName}")
    public ServerResponse add_category(HttpSession session,
                                       @PathVariable("parentId") Integer parentId,
                                       @PathVariable("categoryName") String categoryName){

        return  categoryService.add_category(parentId,categoryName);
    }

    /**
     * 修改节点
     * */
    @RequestMapping(value = "/set_category_name/{categoryId}/{categoryName}")
    public ServerResponse set_category_name(HttpSession session,
                                            @PathVariable("categoryId") Integer categoryId,
                                            @PathVariable("categoryName") String categoryName){

        return  categoryService.set_category_name(categoryId,categoryName);
    }

    /**
     * 获取当前分类id及递归子节点categoryId
     * */
    @RequestMapping(value = "/get_deep_category/categoryId/{categoryId}")
    public ServerResponse get_deep_category(HttpSession session,
                                            @PathVariable("categoryId") Integer categoryId){

        return  categoryService.get_deep_category(categoryId);
    }

}
