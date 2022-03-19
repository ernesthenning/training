package filter;


import java.io.IOException;
import java.util.List;

import bean.UserAccount;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import request.UserRoleRequestWrapper;
import util.AppUtils;
import util.SecurityUtils;

@WebFilter("*/")
public class SecurityFilter implements Filter {
    public SecurityFilter() {
        
    }
    public void destroy() {
        
    }
    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());
        HttpServletRequest wrapRequest = request;
        
        if (loginedUser != null) {
            String userName = loginedUser.getUserName();
            List<String> roles = loginedUser.getRoles();
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }
        if (SecurityUtils.isSecurityPage(request)) {
            if (loginedUser == null) {
                String requestURI = request.getRequestURI();
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestURI);
                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }
            if (!SecurityUtils.hasPermission(wrapRequest)) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
        
    }
}
