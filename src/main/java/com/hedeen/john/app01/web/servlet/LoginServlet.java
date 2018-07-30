package com.hedeen.john.app01.web.servlet;

import com.hedeen.john.app01.model.User;
import com.hedeen.john.app01.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * The {@code LoginServlet} class purpose is to validate user's credentials.
 * Invalidates the session and creates new one. After that stores user data into session.
 * Redirects to error page if some exception comes from DAO layer.
 */
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authEmail = req.getParameter("authUserEmail");
        UserService userService = UserServiceImpl.getUserService(User.Role.ADMIN);
        User aUser = userService.getByEmail(authEmail);
        HttpSession session = req.getSession();
        session.invalidate();
        session = req.getSession();
        session.setAttribute("authUser", aUser);
        logger.info("{} logged in", aUser.getName());
        session.setAttribute("masters", userService.getAllMasters());
        resp.sendRedirect("schedule");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserService userService = UserServiceImpl.getUserService(User.Role.ADMIN);
        session.setAttribute("masters", userService.getAllMasters());
        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    }
}
