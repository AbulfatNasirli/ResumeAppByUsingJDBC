package com.company.resume.controller;

import dao.UserDaoInter;
import entity.User;
import main.Contex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", value = "/users")
public class UserController extends HttpServlet {

    UserDaoInter userdao = Contex.instaceUserdao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoInter userdao = Contex.instaceUserdao();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        List<User> list = userdao.getAll(name, surname);
        request.setAttribute("list",list);
            request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
