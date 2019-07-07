package cn.x2yu.blog.interceptor;

import cn.x2yu.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BackInterceptor implements HandlerInterceptor {

    private static final String userName = "x2yu";
    private static final String password = "wodecuo3744";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean flag = true;
        User user = (User) request.getSession().getAttribute("user");

        if(null==user){
            response.sendRedirect("login");
            flag = false;
        }else{
            //对账号密码进行验证
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                flag = true;
            }else{
                flag = false;
            }
        }
        return flag;
    }
}
