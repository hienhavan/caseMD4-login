package org.example.caselogin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.caselogin.model.ENUM.ROLE;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandle extends SimpleUrlAuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (role.equals(ROLE.ROLE_ADMIN.getRoleName())) {
                return "/admin";
            }
            if (role.equals(ROLE.ROLE_LECTURER.getRoleName())) {
                return "/lecturer";
            }
            if (role.equals(ROLE.ROLE_STUDENT.getRoleName())) {
                return "/student";
            }
            if (role.equals(ROLE.ROLE_STAFF.getRoleName())) {
                return "/staff";
            }
        }
        return "/";
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        if (targetUrl != null && !targetUrl.isEmpty()) {
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            super.handle(request, response, authentication);
        }
    }
}
