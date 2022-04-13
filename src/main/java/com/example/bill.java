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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class bill extends HttpServlet {

    private static final long serialVersionUID = 2161581691453946987L;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /* Usage of some methods in HttpServletResponse and ServletResponse interfaces */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='./styling/style.css'>";
        out.println("<head><title>Checkout Bill</title>"+cssTag+"</head>");
        out.println("<body>");
        try{
            out.println("<br>");
            out.println("<center><h2>Your Total Bill</h2></center>");
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database2", "root", "root");
            System.out.println("Connected database successfully...");
            Statement smt = conn.createStatement();  
            ResultSet result = smt.executeQuery("select * from products");
            int prices[] = new int[4];
            int x=0;
            while(result.next()){
                prices[x++] = result.getInt(3);
            }
            
            int qty1 = Integer.parseInt(request.getParameter("q1"));
            int qty2 = Integer.parseInt(request.getParameter("q2"));
            int qty3 = Integer.parseInt(request.getParameter("q3"));
            int qty4 = Integer.parseInt(request.getParameter("q4"));

            double totalBillAmount = qty1*prices[0] + qty2*prices[1] + qty3*prices[2] + qty4*prices[3];
            
            //System.out.println(totalBillAmount);
            out.println("<center>");
            out.println("<table cellspacing='10px' cellpadding='10px'>");
            out.println("<tr>");
            out.println("<th>Product Description</th>");     
            out.println("<th>Qnty.</th>");      
            out.println("<th>Price</th>");      
            out.println("</tr>");   
            out.println("<tr>");   
            out.println("<td id='pd1'></td>");     
            out.println("<td id='qt1'></td>");     
            out.println("<td id='pr1'></td>");     
            out.println("</tr>"); 
            out.println("<tr>");   
            out.println("<td id='pd2'></td>");     
            out.println("<td id='qt2'></td>");     
            out.println("<td id='pr2'></td>");     
            out.println("</tr>"); 
            out.println("<tr>");   
            out.println("<td id='pd3'></td>");     
            out.println("<td id='qt3'></td>");     
            out.println("<td id='pr3'></td>");     
            out.println("</tr>"); 
            out.println("<tr>");   
            out.println("<td id='pd4'></td>");     
            out.println("<td id='qt4'></td>");     
            out.println("<td id='pr4'></td>");        
            out.println("</tr>");   
            out.println("</table>");
            out.println("</center>");

            if(qty1!=0){
                out.println("<script> document.getElementById('pd1').innerHTML = 'Shirt'; document.getElementById('qt1').innerHTML = "+qty1+"; document.getElementById('pr1').innerHTML = "+(qty1*prices[0])+"</script>");
            }
            if(qty2!=0){
                out.println("<script> document.getElementById('pd2').innerHTML = 'Pant'; document.getElementById('qt2').innerHTML = "+qty2+"; document.getElementById('pr2').innerHTML = "+(qty2*prices[1])+"</script>");
            }
            if(qty3!=0){
                out.println("<script> document.getElementById('pd3').innerHTML = 'Coat'; document.getElementById('qt3').innerHTML = "+qty3+"; document.getElementById('pr3').innerHTML = "+(qty3*prices[2])+"</script>");
            }
            if(qty4!=0){
                out.println("<script> document.getElementById('pd4').innerHTML = 'T-Shirt'; document.getElementById('qt4').innerHTML = "+qty4+"; document.getElementById('pr4').innerHTML = "+(qty4*prices[3])+"</script>");
            }
            out.println("<br><br>");
            out.println("<center><h3>Total payable amount:"+totalBillAmount+"</h3></center>"); 
            out.println("</body>");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            //System.out.println(dtf.format(now));  
            
            smt.executeUpdate("insert into bill(billingtime,billamount) values('" + dtf.format(now) + "','" + totalBillAmount + "')");
            System.out.print("\nBill saved into the database successfully!\n");
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
