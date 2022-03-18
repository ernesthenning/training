package util;

import java.util.List;
import java.util.Set;

import config.SecurityConfig;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtils {
    
    public static boolean isSecurityPage (HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> roles = SecurityConfig.getAllAppRoles();
        
        for (String r : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(r);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hasPermission (HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> allRoles = SecurityConfig.getAllAppRoles();
        for (String r : allRoles) {
            if (!request.isUserInRole(r)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(r);
            if (urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
