package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class reports extends HttpServlet{
    public boolean fetch(int user_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from reports where user_id='" + user_id + "'";
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
                dataList.add(rs.getString("date"));
                dataList.add(rs.getString("task_completed"));
                dataList.add(rs.getString("task_in_progress"));
                dataList.add(rs.getString("next_day_task"));
                dataList.add(rs.getString("problems"));
                dataList.add(rs.getString("supervisor_comment"));
                dataList.add(rs.getString("score"));
            }
        
            // 7. Close all connections
            st.close();
            con.close();
            request.setAttribute("data", dataList);
            request.getRequestDispatcher("Student/viewreports.jsp").forward(request, response);
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
        int user_id = (int)session.getAttribute("ID");
        String go = "Your details have been saved";
        String dont = "Something went wrong, Data failed to display!";

        if(!fetch(user_id, request, response)){
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("Student/studhome.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}