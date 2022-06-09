package com.company.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.resume.util.ControllerUtil;
import dao.UserDaoInter;
import entity.User;
import main.Contex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    UserDaoInter userdao = Contex.instaceUserdao();
    private BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("login.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           String email = request.getParameter("email");
           String password = request.getParameter("password");
           System.out.println("password "+password);
           User user = userdao.findByEmail(email);
           if (user == null) {
               throw new IllegalArgumentException("User dosn't exist !!!");
           }
           BCrypt.Result rs = BCrypt.verifyer().verify(password.toCharArray(),user.getPassword().toCharArray());
           if(!rs.verified){
               throw new IllegalArgumentException("Password is incorrect");
           }
           request.getSession().setAttribute("loggedInUser", user);
           response.sendRedirect("users");
       } catch (Exception ex) {
           ControllerUtil.errorPage(response,ex);
       }
    }
}
