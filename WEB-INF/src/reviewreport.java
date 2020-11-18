package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class reviewreport extends HttpServlet{
    public boolean fetch(int report_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from reports where ID='" + report_id + "'";
        try {
            //1. Loading the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // 3. Establish the connection
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            // 4. Create a statement
            Statement st = con.createStatement();
        
            // 5. Execute a Query
            ResultSet result = st.executeQuery(query);
            if(result.next()){
                // 6. Process the results
                String week_ending = result.getString("date");
                String task_completed = result.getString("task_completed");
                String task_in_progress = result.getString("task_in_progress");
                String next_day_task = result.getString("next_day_task");
                String problems = result.getString("problems");
                String supervisor_comment = result.getString("supervisor_comment");
                int score = result.getInt("score");
                int id = result.getInt("id");

                request.setAttribute("week_ending", week_ending);
                request.setAttribute("task_completed", task_completed);
                request.setAttribute("task_in_progress", task_in_progress);
                request.setAttribute("next_day_task", next_day_task);
                request.setAttribute("problems", problems);
                request.setAttribute("supervisor_comment", supervisor_comment);
                request.setAttribute("score", score);
                request.setAttribute("id", id);
                request.getRequestDispatcher("Supervisor/reviewreport.jsp").forward(request, response);
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

    public boolean update(int sup_id, String sup_comment, int score, int report_id){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "update reports set supervisor_comment='"+ sup_comment +"', score='"+ score +"', supervisor_id='"+ sup_id +"' where ID='"+ report_id +"'";
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
        int report_id = Integer.parseInt(request.getParameter("ID"));
        fetch(report_id, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        HttpSession session = request.getSession();
        int sup_id = (int)session.getAttribute("ID");
        String sup_comment = request.getParameter("sup_comment");
        int score = Integer.parseInt(request.getParameter("score"));
        int report_id = Integer.parseInt(request.getParameter("ID"));
        String go = "Details have been saved!";
        String dont = "Something went wrong, please try again!";
        
        if(update(sup_id, sup_comment, score, report_id)){
            request.setAttribute("go", go);
            request.getRequestDispatcher("suphome").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("suphome").forward(request, response);
        } 
    }
}