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
public class updatePrice extends HttpServlet {

    private static final long serialVersionUID = 2161581691453946987L;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        out.println("<head><title>Updation Successful</title>"+cssTag+"</head>");
        out.println("<body>");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database2", "root", "root");
            System.out.println("Connected database successfully...");
            Statement smt = conn.createStatement();

            int pid = Integer.parseInt(request.getParameter("pid"));
            int updatedprice = Integer.parseInt(request.getParameter("uprice"));
            ResultSet result = smt.executeQuery("select * from products where pid=" + pid + "");
            System.out.print("Old values\n");
            while (result.next()) {
                System.out.print(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getInt(3) + "\n");
            }
            smt.executeUpdate(
                    "update products set price = " + updatedprice + " where pid=" + pid + "");
            System.out.print("\nPrice of the wardrobe successfully updated!\n");
            ResultSet result1 = smt.executeQuery("select * from products where pid=" + pid + "");
            System.out.print("Updated values\n");
            while (result1.next()) {
                System.out.print(result1.getInt(1) + "\t" + result1.getString(2) + "\t" + result1.getInt(3) + "\n");
            }
            
            out.println("<br><br>");
            out.println("<center><h2>Price of the wardrobe updated successfully!</h2></center>");
            out.println("</body>");
            
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
