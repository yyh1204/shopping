package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 登录
     * */
    @RequestMapping("/login/{username}/{password}")
    public ServerResponse login(HttpSession session,
                                @PathVariable("username") String username,
                                @PathVariable("password") String password){
        ServerResponse serverResponse = userService.login(username,password);
        if(serverResponse.isScuess()){//登陆成功
            UserInfo userInfo = (UserInfo) serverResponse.getDate();
            session.setAttribute(Const.CURRENTUDER,userInfo);
        }
        return serverResponse;
    }

    /**
     * 注册
     * */
    @RequestMapping("/register.do")
    public ServerResponse login(HttpSession session, UserInfo userInfo){
        ServerResponse serverResponse = userService.register(userInfo);

        return serverResponse;
    }

    /**
     * 根据用户名查询密保问题
     * */
    @RequestMapping("/forget_get_question/username/{username}")
    public ServerResponse login(@PathVariable("username") String username){
        ServerResponse serverResponse = userService.forget_get_question(username);

        return serverResponse;
    }

    /**
     * 提交问题答案
     * */
    @RequestMapping("/forget_check_answer/{username}/{question}/{answer}")
    public ServerResponse forget_check_answer(@PathVariable("username") String username,
                                              @PathVariable("question") String question,
                                              @PathVariable("answer") String answer){
        ServerResponse serverResponse = userService.forget_check_answer(username,question,answer);

        return serverResponse;
    }

    /**
     * 忘记密码的重置密码
     * */
    @RequestMapping("/forget_reset_password/{username}/{passwordNew}/{forgetToken}")
    public ServerResponse forget_reset_password(@PathVariable("username") String username,
                                                @PathVariable("passwordNew") String passwordNew,
                                                @PathVariable("forgetToken") String forgetToken){
        ServerResponse serverResponse = userService.forget_reset_password(username,passwordNew,forgetToken);

        return serverResponse;
    }

    /**
     * 检查用户名或者邮箱是否有效
     * */
    @RequestMapping("/check_valid/{str}/{type}")
    public ServerResponse check_valid(@PathVariable("str") String str,
                                      @PathVariable("type") String type){
        ServerResponse serverResponse = userService.check_valid(str,type);

        return serverResponse;
    }

    /**
     * 获取登录用户信息
     * */
    @RequestMapping("/get_user_info.do")
    public ServerResponse get_user_info(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        userInfo.setPassword("");
        userInfo.setQuestion("");
        userInfo.setAnswer("");
        userInfo.setRole(null);
        return ServerResponse.serverResponsebBySuccess(userInfo);
    }

    /**
     * 登录中状态重置密码
     * */
    @RequestMapping("/reset_password/{passwordOld}/{passwordNew}")
    public ServerResponse reset_password(HttpSession session,
                                         @PathVariable("passwordOld") String passwordOld,
                                         @PathVariable("passwordNew") String passwordNew){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        return userService.reset_password(userInfo.getUsername(),passwordOld,passwordNew);
    }

    /**
     * 登录状态更新个人信息
     * */
    @RequestMapping("/update_information.do")
    public ServerResponse update_information(HttpSession session, UserInfo user){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        user.setId(userInfo.getId());
        ServerResponse serverResponse = userService.update_information(user);
        if(serverResponse.isScuess()){
            //更新session中的用户信息
            UserInfo userInfo1 = userService.findUserInfoByUserid(userInfo.getId());
            session.setAttribute(Const.CURRENTUDER,userInfo1);
        }
        return serverResponse;
    }

    /**
     * 获取登录用户详细信息
     * */
    @RequestMapping("/get_inforamtion.do")
    public ServerResponse get_inforamtion(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUDER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        userInfo.setPassword("");
        return ServerResponse.serverResponsebBySuccess(userInfo);
    }

    /**
     * 退出登录
     * */
    @RequestMapping("/logout.do")
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENTUDER);
        return ServerResponse.serverResponsebBySuccess();
    }
}

