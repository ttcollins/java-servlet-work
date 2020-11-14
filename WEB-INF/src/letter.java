package NAD;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class letter extends HttpServlet{
    public boolean fetch(int user_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from users where id='" + user_id + "'";
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
                int student_id = result.getInt("ID");

                request.setAttribute("fname", fname);
                request.setAttribute("other", other);
                request.setAttribute("gender", gender);
                request.setAttribute("number", number);
                request.setAttribute("email", email);
                request.getRequestDispatcher("Student/letter.jsp").forward(request, response);
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
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        int user_id = (int)session.getAttribute("ID");
        fetch(user_id, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
}