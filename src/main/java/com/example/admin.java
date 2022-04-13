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
public class admin extends HttpServlet {

    private static final long serialVersionUID = 2161581691453946987L;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        out.println("<head><title>Admin Portal</title>"+cssTag+"</head>");
        out.println("<body>");
        try{
            if(request.getParameter("pswd").equals("admin")){
                out.println("<center><h2>Welcome to admin Portal!</h2></center>");
                out.println("<br><br>");
                out.println("<h2> Update price of the wardrobes <h2>");
                out.println("<form action='update_price' method='post'>");
                out.println("Enter product id: <input type='text' name='pid'>");
                out.println("<br><br>");
                out.println("Enter updated price: <input type='text' name='uprice'>");
                out.println("<br><br>");
                out.println("<input type='submit' value='Update Price' onclick='update()'>");
                out.println("</form>");
                out.println("<br><br>");
                out.println("<h2> Delete a wardrobe <h2>");
                out.println("<form action='delete_product' method='post'>");
                out.println("Enter product id: <input type='text' name='prodid'>");
                out.println("<br><br>");
                out.println("<input type='submit' value='Delete Wardrobe' onclick='delete()'>");
                out.println("</form>");
                out.println("<script> function update() { alert('Price updated successfully!'); }</script>");
                out.println("<script> function delete() { alert('Wardrobe deleted successfully!'); }</script>");
                out.println("</body>");
            }
            else{
                out.println("<script> alert('Invalid admin password!'); </script>");
                out.println("<p>Go Back to <a href='index.html'>Login</a> Page</p>");
            }
            /*
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database2", "root", "root");
            System.out.println("Connected database successfully...");
            Statement smt = conn.createStatement(); */
           
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
