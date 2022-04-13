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
public class servlet extends HttpServlet {

    private static final long serialVersionUID = 2161581691453946987L;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        out.println("<head><title>Servlet Home Page</title>"+cssTag+"</head>");
        out.println("<body>");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database2", "root", "root");
            System.out.println("Connected database successfully...");
            Statement smt = conn.createStatement();
            ResultSet result = smt.executeQuery("select * from user");
            String username[] = new String[10];
            String password[] = new String[10];
            int x=0;
            while (result.next()) {
                username[x] = result.getString(1);
                password[x] = result.getString(2);
                x++;
            }
            
            ResultSet result2 = smt.executeQuery("select * from products");
            int productid[] = new int[4];
            String productname[] = new String[4];
            int productprices[] = new int[4];
            int y=0;
            while(result2.next()){
                 productid[y] = result2.getInt(1);
                 productname[y] = result2.getString(2);
                 productprices[y] = result2.getInt(3);
                 y++;
            }
            int fl=0;
            for(int i=0; i<x; i++){
                if(request.getParameter("uname").equals(username[i]) && request.getParameter("pass").equals(password[i])){
                    
                    String user=request.getParameter("suname");
                    out.println("<center><h2> Welcome "+username[i]+" to the Wardrobe Portal!</h2></center>");
                    out.println("<br><br>");
                    out.println("<center>");
                    out.println("<form action='bill' method='post'>");
                    out.println("<table cellspacing='15px' cellpadding='10px'>");
                    out.println("<tr>");
                    out.println("<th>Product ID</th>");      
                    out.println("<th>Product Name</th>");     
                    out.println("<th>Price (in rupees)</th>");      
                    out.println("<th>Quantity</th>");      
                    out.println("<th>Buy item</th>");      
                    out.println("</tr>");   
                    out.println("<tr>");   
                    out.println("<td>"+ productid[0] +"</td>");     
                    out.println("<td>"+ productname[0] +"</td>");     
                    out.println("<td>"+ productprices[0] +"</td>");   
                    out.println("<td><input type='number' name='q1' value='0''></td>");     
                    out.println("<td><button type='button' name='b1' onclick='purchase()'>Add to cart</button></td>");     
                    out.println("</tr>"); 
                    out.println("<tr>");   
                    out.println("<td>"+ productid[1] +"</td>");     
                    out.println("<td>"+ productname[1] +"</td>");     
                    out.println("<td>"+ productprices[1] +"</td>");   
                    out.println("<td><input type='number' name='q2' value='0''></td>");     
                    out.println("<td><button type='button' name='b2' onclick='purchase()'>Add to cart</button></td>");     
                    out.println("</tr>"); 
                    out.println("<tr>");   
                    out.println("<td>"+ productid[2] +"</td>");     
                    out.println("<td>"+ productname[2] +"</td>");     
                    out.println("<td>"+ productprices[2] +"</td>");     
                    out.println("<td><input type='number' name='q3' value='0''></td>");     
                    out.println("<td><button type='button' name='b3' onclick='purchase()'>Add to cart</button></td>");     
                    out.println("</tr>"); 
                    out.println("<tr>");   
                    out.println("<td>"+ productid[3] +"</td>");     
                    out.println("<td>"+ productname[3] +"</td>");     
                    out.println("<td>"+ productprices[3] +"</td>");     
                    out.println("<td><input type='number' name='q4' value='0''></td>");     
                    out.println("<td><button type='button' name='b4' onclick='purchase()'>Add to cart</button></td>");     
                    out.println("</tr>");   
                    out.println("</table>");
                    /* ServletConfig interface methods application */
                    ServletConfig sc=getServletConfig();
                    out.println("<br><br>");
                    out.println("<input type='submit' name='' value='Calculate Bill'>");
                    out.println("</form>");
                    out.println("</center>");   
                    out.println("<script> function purchase(){ alert('Item added to cart successfully!'); }</script>");

                    out.println("</body>");
                   
                    fl=1;
                    break;
                }
            }
            if(fl==0){
                out.println("<script>alert('Invalid username/password')</script>");
                out.println("<h3>Please enter correct username/password!</h3>");
                out.println("<p>Go Back to <a href='index.html'>Login</a> Page</p>");
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
