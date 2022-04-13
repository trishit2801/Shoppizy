package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class adminlogin extends HttpServlet {

    private static final long serialVersionUID = 2161581691453946987L;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        out.println("<head><title>Admin Login</title>"+cssTag+"</head>");
        out.println("<body>");
        try{
            out.println("<br><br><br>");
            out.println("<center>");
            out.println("<h2>ADMIN LOGIN</h2>");
            out.println("<form action='admin_api' method='post'>");
            out.println("Password:<input type='password' name='pswd'>");
            out.println("<br> <br>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
            out.println("</center>");

        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            out.close();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
