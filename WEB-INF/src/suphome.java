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
         dataList.add(rs.getString("std_number"));
         dataList.add(rs.getString("reg_number"));
         dataList.add(rs.getString("course"));
         dataList.add(rs.getString("supervisor_id"));
         dataList.add(rs.getString("user_id"));
         dataList.add(rs.getInt("placement_id"));
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

    public boolean fetchstudnumber(int sup_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select count(supervisor_id) as studnumber from student where supervisor_id='" + sup_id + "'";
      try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet rs = st.executeQuery(query);
        // 6. Process the results
        if(rs.next()) {
            int studnumber = rs.getInt("studnumber");
            String query1 = "update supervisor set number_of_students='"+ studnumber +"' where user_id='"+ sup_id +"'";
            int rs1 = st.executeUpdate(query1);
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }    
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