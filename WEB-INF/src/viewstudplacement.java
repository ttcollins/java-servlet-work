package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/viewstudplacement")
public class viewstudplacement extends HttpServlet{
    public boolean fetch(int placement_id, HttpServletRequest request, HttpServletResponse response){
         // 2. Define the Connection URL
         String url = "jdbc:mysql://localhost/NAD";
      
         String dbUsername = "root";
         String dbPassword = "";
         String query = "select * from placement where id='" + placement_id + "'";
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
             String field_email = result.getString("field_email");
             String field_fname = result.getString("field_fname");
             String field_other = result.getString("field_other");
             String field_number = result.getString("field_number");
             String start_date = result.getString("start_date");
             String end_date = result.getString("end_date");
             String org_name = result.getString("org_name");
             String org_address = result.getString("org_address");
             String additional_address_info = result.getString("additional_address_info");
             String org_contact = result.getString("org_contact");
             String org_email = result.getString("org_email");
             request.setAttribute("field_email", field_email);
             request.setAttribute("field_fname", field_fname);
             request.setAttribute("field_other", field_other);
             request.setAttribute("field_number", field_number);
             request.setAttribute("start_date", start_date);
             request.setAttribute("end_date", end_date);
             request.setAttribute("org_name", org_name);
             request.setAttribute("org_address", org_address);
             request.setAttribute("additional_address_info", additional_address_info);
             request.setAttribute("org_contact", org_contact);
             request.setAttribute("org_email", org_email);
             request.getRequestDispatcher("HOD/viewstudplacement.jsp").forward(request, response);
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
        int placement_id = Integer.parseInt(request.getParameter("placement"));
        fetch(placement_id, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}