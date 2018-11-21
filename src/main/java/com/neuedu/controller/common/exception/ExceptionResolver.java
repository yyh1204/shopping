package com.neuedu.controller.common.exception;

import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 * @Component
 * @Service
 * @Repository
 * @Controller
 * */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

        modelAndView.addObject("status", ResponseCode.ERROR);
        modelAndView.addObject("msg", "接口调用出错");
        modelAndView.addObject("data", e.toString());

        return modelAndView;
    }
}
