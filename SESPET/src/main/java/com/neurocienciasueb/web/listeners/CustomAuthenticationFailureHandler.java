package com.neurocienciasueb.web.listeners;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    private String defaultFailureUrl;
    private boolean forwardToDestination = false;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    public CustomAuthenticationFailureHandler() {
        this.setDefaultFailureUrl("/login.xhtml");
    }
    
    public CustomAuthenticationFailureHandler(String defaultFailureUrl) {
        setDefaultFailureUrl(defaultFailureUrl);
    }
        
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        
        if (defaultFailureUrl == null) {
            logger.debug("No failure URL set, sending 401 Unauthorized error");
            
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
        } else {
            if (forwardToDestination) {
                logger.debug("Forwarding to " + defaultFailureUrl);
                
                request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
            } else {
                logger.debug("Redirecting to " + defaultFailureUrl);
                
                redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
            }
        }
    }
    
    public void setDefaultFailureUrl(String defaultFailureUrl) {        
        this.defaultFailureUrl = defaultFailureUrl;
    }
    
    protected boolean isUseForward() {
        return forwardToDestination;
    }
    
    public void setUseForward(boolean forwardToDestination) {
        this.forwardToDestination = forwardToDestination;
    }
    
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
