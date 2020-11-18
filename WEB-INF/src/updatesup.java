package NAD;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class updatesup extends HttpServlet{
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
            String fname = result.getString("fname");
            String other = result.getString("other");
            String gender = result.getString("gender");
            String number = result.getString("number");

            HttpSession session = request.getSession();
            request.setAttribute("fname", fname);
            request.setAttribute("other", other);
            request.setAttribute("gender", gender);
            request.setAttribute("number", number);
            request.getRequestDispatcher("Supervisor/updatesup.jsp").forward(request, response);
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

    public boolean update(String fname, String other, String gender, String number, String email, String password, int user_id){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "update users set fname='"+ fname +"', other='"+ other +"', gender='"+ gender +"', number='"+ number +"', email='"+ email+"', password='"+ password +"' where ID='"+ user_id +"'";
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
        String email = session.getAttribute("email").toString();
        fetch(email, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        HttpSession session = request.getSession();
        String fname = request.getParameter("fname");
        String other = request.getParameter("other");
        String gender = request.getParameter("gender");
        String number = request.getParameter("number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int user_id = (int) session.getAttribute("ID");
        String go = "Your Details have been saved!";
        String dont = "Something went wrong, please try again!";

        if(update(fname, other, gender, number, email, password, user_id)){
            session.setAttribute("email", email);
            session.setAttribute("fname", fname);
            request.setAttribute("go", go);
            request.getRequestDispatcher("suphome").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("suphome").forward(request, response);
        } 
   }
}
