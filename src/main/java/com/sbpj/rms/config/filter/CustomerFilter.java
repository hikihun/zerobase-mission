package com.sbpj.rms.config.filter;

import com.sbpj.rms.config.jwt.JwtAuthenticationProvider;
import com.sbpj.rms.config.jwt.UserVo;
import com.sbpj.rms.service.CustomerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if (!jwtAuthenticationProvider.validateToken(token)) {
            throw new ServletException("Invalid Access");
        }
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        customerService.getCustomer(vo.getId(), vo.getEmail()).orElseThrow(
                () -> new ServletException("Invalid Access")
        );
        chain.doFilter(request, response);
    }
}
