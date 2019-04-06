package org.pet.social.filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Test-Header", "Test-Value");
        filterChain.doFilter(request, response);
    }
}
