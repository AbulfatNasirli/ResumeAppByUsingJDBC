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

@WebServlet(name = "ErrorController", value = "/error")
public class ErrorController extends HttpServlet {

    UserDaoInter userdao = Contex.instaceUserdao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }