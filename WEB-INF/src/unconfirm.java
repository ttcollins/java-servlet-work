package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/unconfirm")
public class unconfirm extends HttpServlet{
    public boolean unconfirmvisit(int visit_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String confirm = "NO";
        String query = "update visits set supervisor_confirm='"+ confirm +"' where ID='"+ visit_id +"'";
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
        String go = "Your details have been saved";
        String dont = "Something went wrong, Data failed to display!";
        int visit_id = Integer.parseInt(request.getParameter("ID"));
        
        if(unconfirmvisit(visit_id, request, response)){
            request.setAttribute("go", go);
            request.getRequestDispatcher("unconfirmedvisits").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("suphome").forward(request, response);
        } 
    }
}