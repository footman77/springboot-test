package cn.footman.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author footman77
 * @create 2018-09-20 14:02
 */

//登陆检查
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String username = (String) request.getSession().getAttribute("username");
        if(!StringUtils.isEmpty(username)){
            return true;
        }
        //未登录
        //显示错误消息
        request.setAttribute("msg","请登陆");
        //转发到登陆页面
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
