package com.example.UserPizzaService.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;// typecast
        //expects the token to come from the header
        String authHeader = httpServletRequest.getHeader("Authorization");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.print("Invalid or Token Missing");
            servletOutputStream.close();

        } else {
            String token = authHeader.substring(7);//6+1 = 7
            Claims claims = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(token).getBody();
            httpServletRequest.setAttribute("claims",claims);

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
