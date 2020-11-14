package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class suphome extends HttpServlet{
    public boolean fetchstudents(int sup_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from student where supervisor_id='" + sup_id + "'";
      try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet rs = st.executeQuery(query);
        List dataList = new ArrayList();
        // 6. Process the results
        while (rs.next()) {
         // 7. Close all connections
         int stud_id = rs.getInt("user_id");
         dataList.add(rs.getString("reg_number"));
         dataList.add(rs.getString("std_number"));
         dataList.add(rs.getString("course"));
         /*String query1 = "select * from users where ID='"+ stud_id +"'";
         ResultSet rs1 = st.executeQuery(query1);
         while (rs1.next()) {
            dataList.add(rs.getString("reg_number"));
            dataList.add(rs1.getString("fname"));
            dataList.add(rs1.getString("other"));
            dataList.add(rs1.getString("gender"));
            dataList.add(rs1.getString("number"));
            dataList.add(rs1.getInt("email"));
            dataList.add(rs1.getInt("course"));
         }*/
        }
        
        // 7. Close all connections
        st.close();
        con.close();
        request.setAttribute("data", dataList);
        request.getRequestDispatcher("Supervisor/suphome.jsp").forward(request, response);
      }
       catch (SQLException e) {
        System.out.println(e.toString());
       } catch (Exception e) {
        System.out.println(e.toString());
       }
       return false;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        HttpSession session = request.getSession();
        int sup_id = (int)session.getAttribute("ID");
        String go = "Your details have been saved";
        String dont = "Something went wrong, Data failed to display!";

        if(!fetchstudents(sup_id, request, response)){
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("Supervisor/suphome.jsp").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}