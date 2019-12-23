package org.sid.Security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthentificationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("---------------");

        String jwt = httpServletRequest.getHeader("Authorisation");
        if(jwt==null) throw new RuntimeException("Not Authorise");

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
