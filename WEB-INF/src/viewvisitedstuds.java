package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class viewvisitedstuds extends HttpServlet{
    public boolean fetch(HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        int confirm = 1;
        String query = "select * from student where visited='"+confirm+"'";
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
         dataList.add(rs.getString("std_number"));
         dataList.add(rs.getString("reg_number"));
         dataList.add(rs.getString("course"));
         dataList.add(rs.getString("supervisor_id"));
         if(rs.getInt("visited")== 1){
             dataList.add("YES");
         }else{
             dataList.add("NO");
         }
         dataList.add(rs.getInt("user_id"));
        }
        
        // 7. Close all connections
        st.close();
        con.close();
        request.setAttribute("data", dataList);
        request.getRequestDispatcher("HOD/viewvisitedstuds.jsp").forward(request, response);
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
        String dont = "Something went wrong, Data failed to display!";

        if(!fetch(request, response)){
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("HOD/hodhome").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}