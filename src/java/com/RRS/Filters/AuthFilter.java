package com.RRS.Filters;

import com.RRS.DAO.UserDAO;
import com.RRS.Models.Message;
import com.RRS.Models.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            Message msg;
            User user = (User) session.getAttribute("visitor");

            if (user != null) {

                chain.doFilter(request, response);
            } else {
                msg = new Message("You must login first to access this page", Message.ALERT_DANGER);
                session.setAttribute("userMsg", msg);
                res.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
