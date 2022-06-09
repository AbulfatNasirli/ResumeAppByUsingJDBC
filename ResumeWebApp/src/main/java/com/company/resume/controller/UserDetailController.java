package com.company.resume.controller;

import entity.User;
import dao.UserDaoInter;
import main.Contex;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserDetailController", value = "/userdetail")
public class UserDetailController extends HttpServlet {

    UserDaoInter userdao = Contex.instaceUserdao();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String action = request.getParameter("action");
            if (action.equals("update")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                User user = userdao.getById(id);

                System.out.println("name :" + name);
                System.out.println("surname :" + surname);

                user.setName(name);
                user.setSurname(surname);
                userdao.updateUser(user);
            } else if (action.equals("delete")) {
                userdao.removeUser(id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalAccessException("Id is not specified");
            }
            Integer userId = Integer.parseInt(request.getParameter("id"));
            UserDaoInter userDao = Contex.instaceUserdao();
            User u = userDao.getById(userId);
            if (u == null) {
                throw new IllegalArgumentException("There is no user is this id");
            }
            request.setAttribute("user", u);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
