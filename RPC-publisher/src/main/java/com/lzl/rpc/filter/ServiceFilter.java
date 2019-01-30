package com.lzl.rpc.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;
@Component
@WebFilter(filterName="serviceFilter",urlPatterns="/*")
public class ServiceFilter implements Filter
{   
    public static boolean isOpen = true;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        if(isOpen) {
            System.out.println("服务上线");
            chain.doFilter(request, response);
        }else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("已下线，不能提供服务");
        }
    }   
}
