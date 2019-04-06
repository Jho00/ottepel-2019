package org.pet.social.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletRequest = (HttpServletResponse)response;
        httpServletRequest.addHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);
    }
}
