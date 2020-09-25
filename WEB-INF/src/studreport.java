package examples;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/studreport")
public class studreport extends HttpServlet{
    public boolean fill(String date, String task_completed, String task_in_progress, String next_day_tasks, String problems, int user_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "insert into reports (user_id, date, task_completed, task_in_progress, next_day_task, problems) VALUES('"+user_id+"', '"+date+"', '"+task_completed+"', '"+task_in_progress+"', '"+next_day_tasks+"', '"+problems+"')";
      try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        int rs = st.executeUpdate(query);
        
        // 6. Process the results
        if (rs!=0) {
         // 7. Close all connections
         st.close();
         con.close();
         return true;
        }
        
        // 7. Close all connections
        st.close();
        con.close();
        return false;
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
        String date = request.getParameter("date");
        String task_completed = request.getParameter("task_completed");
        String task_in_progress = request.getParameter("task_in_progress");
        String next_day_tasks = request.getParameter("next_day_tasks");
        String problems = request.getParameter("problems");
        int user_id = (int)session.getAttribute("ID");
        String go = "Your details have been saved";
        String dont = "Something went wrong, Please try again!";

        if(fill(date, task_completed, task_in_progress, next_day_tasks, problems, user_id, request, response)){
            request.setAttribute("go", go);
            request.getRequestDispatcher("studhome.jsp").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("studreprot.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}