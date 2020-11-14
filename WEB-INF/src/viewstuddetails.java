package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/viewstuddetails")
public class viewstuddetails extends HttpServlet{
    public boolean fetch(int stud_id, int placement_id, HttpServletRequest request, HttpServletResponse response){
         // 2. Define the Connection URL
         String url = "jdbc:mysql://localhost/NAD";
      
         String dbUsername = "root";
         String dbPassword = "";
         String query = "select * from users where id='" + stud_id + "'";
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
             String fname = result.getString("fname");
             String other = result.getString("other");
             String gender = result.getString("gender");
             String number = result.getString("number");
             String email = result.getString("email");
             int role = result.getInt("role");
             int user_id = result.getInt("ID");
 
             request.setAttribute("fname", fname);
             request.setAttribute("other", other);
             request.setAttribute("gender", gender);
             request.setAttribute("number", number);
             request.setAttribute("email", email);
             request.setAttribute("role", role);
             request.setAttribute("user_id", user_id);
             request.setAttribute("placement_id", placement_id);
             request.getRequestDispatcher("HOD/viewstuddetails.jsp").forward(request, response);
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
        int stud_id = Integer.parseInt(request.getParameter("ID"));
        int placement_id = Integer.parseInt(request.getParameter("placement_id"));
        fetch(stud_id, placement_id, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}