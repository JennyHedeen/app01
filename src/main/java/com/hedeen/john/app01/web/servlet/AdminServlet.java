package com.hedeen.john.app01.web.servlet;

import com.hedeen.john.app01.model.User;
import com.hedeen.john.app01.service.UserService;
import com.hedeen.john.app01.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        UserService userService = UserServiceImpl.getUserService(User.Role.ADMIN);

        String action = req.getParameter("action");
        switch (action == null ? "all" : action) {
            case "edit":
            case "create":
                final User user = "create".equals(action) ?
                        new User() : userService.get(Integer.parseInt(req.getParameter("userid")));
                req.setAttribute("user", user);
                req.getRequestDispatcher("jsp/userForm.jsp").forward(req, resp);
                break;
            case "delete":
                userService.delete(Integer.parseInt(req.getParameter("userid")));
                resp.sendRedirect("users");
                break;
            case "all":
                List<User> users = userService.getUsers();

                req.setAttribute("users", users);
                req.getRequestDispatcher("jsp/users.jsp").forward(req, resp);
                break;
            default:
//                Paginator<User> paginator = new Paginator<>();
//                List<User> users = userService.getAll();
//                int page = req.getParameter("page")!=null ? Integer.parseInt(req.getParameter("page")) : 1;
//                int pagesCount = paginator.getPageCount(users);
//                page = page > pagesCount ? pagesCount : page < 1 ? 1 : page;
//                req.setAttribute("users", paginator.getPage(users, page));
//                req.setAttribute("page", page);
//                req.setAttribute("pagesCount", paginator.getPageCount(users));
                req.getRequestDispatcher("jsp/users.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String regName = req.getParameter("name");
        String regEmail = req.getParameter("email");
        String regPassword = req.getParameter("password");
        String regRole = req.getParameter("role");

        User user = new User(regName, regEmail, regPassword, User.Role.valueOf(regRole));

        UserService userService = UserServiceImpl.getUserService(User.Role.ADMIN);
        if (req.getParameter("userid").isEmpty()) {
            userService.create(user);
        }
        else {
            user.setId(Integer.parseInt(req.getParameter("userid")));
            userService.update(user);
        }
        resp.sendRedirect("users");
    }
}
