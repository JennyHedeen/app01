package com.hedeen.john.app01.web.servlet;

import com.hedeen.john.app01.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User authUser = (User)session.getAttribute("authUser");
        Integer masterId;

        String mid = req.getParameter("mid");
        if(mid == null) {
            masterId = (Integer)session.getAttribute("masterId");
        } else {
            masterId = Integer.parseInt(mid);
            if(getMasterFromSession(session, masterId) != null) {
                session.setAttribute("masterId", masterId);
            } else {
                req.setAttribute("message", "minvalid");
                req.getRequestDispatcher("/jsp/errorPage.jsp").forward(req, resp);
            }
        }

        if(masterId!=null) {
            req.setAttribute("activeMaster", getMasterFromSession(session, masterId));
//            appointments = service.getAllByDate(masterId, localDate);
//            req.setAttribute("date", localDate);
//            req.setAttribute("apps", appointments);
            req.getRequestDispatcher("jsp/schedule.jsp").forward(req, resp);
        }
    }

    private User getMasterFromSession(HttpSession session, Integer masterId) {
        List<User> list = (List<User>) session.getAttribute("masters");
        for(User u : list) {
            if (u.getId().equals(masterId)) {
                return u;
            }
        }
        return null;
    }
}
