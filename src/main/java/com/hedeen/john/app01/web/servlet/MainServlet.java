package com.hedeen.john.app01.web.servlet;

import com.hedeen.john.app01.model.User;
import com.hedeen.john.app01.service.UserService;
import com.hedeen.john.app01.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        UserService userService = UserServiceImpl.getUserService();
        List<User> users = userService.getUsers();

        PrintWriter pw = resp.getWriter();
        pw.write(users.get(0).toString());
    }
}
