package com.etoak.interceptor;

import com.etoak.bean.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("URL:{}", request.getRequestURI());

        HttpSession session = request.getSession();
        Owner owner = (Owner) session.getAttribute("user");
        if(owner == null) {
            response.sendRedirect(request.getContextPath() + "/owner/to_login");
            return false;
        }

        return true;
    }
}
