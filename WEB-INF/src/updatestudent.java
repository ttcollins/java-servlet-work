package examples;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/updatestudent")
public class updatestudent extends HttpServlet{
    public boolean fetch(String email, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from users where email='" + email + "'";
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
            int user_id = result.getInt("ID");
            String fname = result.getString("fname");
            String other = result.getString("other");
            String gender = result.getString("gender");
            String number = result.getString("number");
            String query1 = "select * from student where user_id='"+user_id+"'"; 
            ResultSet rs = st.executeQuery(query1);
            if(rs.next()){
                String std_number = rs.getString("std_number");
                String reg_number = rs.getString("reg_number");
                String course = rs.getString("course");
                request.setAttribute("std_number", std_number);
                request.setAttribute("reg_number", reg_number);
                request.setAttribute("course", course);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user_id);
            request.setAttribute("fname", fname);
            request.setAttribute("other", other);
            request.setAttribute("gender", gender);
            request.setAttribute("number", number);
            request.getRequestDispatcher("updatestud.jsp").forward(request, response);
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
        String email = session.getAttribute("email").toString();
        fetch(email, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}
