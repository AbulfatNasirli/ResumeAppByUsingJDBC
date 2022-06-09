package com.company.resume.filter;

import com.company.resume.util.ControllerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "SecurityFilter",urlPatterns = {"*"})
public class SecurityFilter implements Filter {
    public  void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
       try {
           HttpServletResponse res = (HttpServletResponse) response;
           HttpServletRequest req = (HttpServletRequest) request;

           if (!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser") == null) {
              res.sendRedirect("login");
               // ControllerUtil.errorPage(res, new IllegalAccessException("Not found !!!"));
           }
           else{
               chain.doFilter(request, response);
           }
       } catch (Exception ex){
           ex.printStackTrace();
       }
    }

    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}