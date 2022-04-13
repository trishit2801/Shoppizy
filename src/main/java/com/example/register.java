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
public class register extends HttpServlet {

    private static final long serialVersionUID = 2161581691453946987L;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        out.println("<head><title>Registration Successful</title>"+cssTag+"</head>");
        out.println("<body>");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database2", "root", "root");
            System.out.println("Connected database successfully...");
            Statement smt = conn.createStatement();
            
            if(request.getParameter("suname")!=null && request.getParameter("spass")!=null){
               
                String username = request.getParameter("suname");
                String password = request.getParameter("spass");
                smt.executeUpdate("insert into user(username,password) values('" + username + "','" + password + "')");
                System.out.print("\nUser registered successfully!\n");
                out.println("<h2>You have been registered successfully!</h2>");
                //out.println("<h2> Welcome "+username+" to the Wardrobe Portal!</h2>");
                out.println("<p>Go Back to <a href='index.html'>Login</a> Page</p>");
                /* ServletConfig interface methods application */
                ServletConfig sc=getServletConfig();
    
                out.println("</body>");
            }
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
