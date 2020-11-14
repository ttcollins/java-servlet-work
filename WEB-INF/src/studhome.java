package NAD;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class studhome extends HttpServlet{
    public boolean fetchplacement(int user_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from placement where user_id='" + user_id + "'";
      try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet result = st.executeQuery(query);
        HttpSession session = request.getSession();

        // 6. Process the results
        if (result.next()) {
            String check = "checked";
            session.setAttribute("status", check);

            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }else{
            String check = "";
            session.setAttribute("status", check);
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }

       }
       catch (SQLException e) {
        System.out.println(e.toString());
       } catch (Exception e) {
        System.out.println(e.toString());
       }
       return false;
    }

    public boolean fetchreports(int user_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select count(user_id) as number from reports where user_id='"+user_id+"'";
        try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet result = st.executeQuery(query);
        HttpSession session = request.getSession();
        
        // 6. Process the results
        if (result.next()) {
            int report_number = result.getInt("number");
            session.setAttribute("report_number", report_number);
    
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }else{
            int report_number = 0;
            session.setAttribute("report_number", report_number);
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }

       }
       catch (SQLException e) {
        System.out.println(e.toString());
       } catch (Exception e) {
        System.out.println(e.toString());
       }
       return false;
    }

    public boolean fetchconfirmed(int user_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String confirm = "YES";
        String query = "select count(stud_id) as number from visits where stud_id='"+user_id+"' and supervisor_confirm='"+confirm+"'";
        try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet result = st.executeQuery(query);
        HttpSession session = request.getSession();

        // 6. Process the results
        if (result.next()) {
            int confirm_number = result.getInt("number");
            session.setAttribute("confirm_number", confirm_number);
    
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }else{
            int confirm_number = 0;
            session.setAttribute("confirm_number", confirm_number);
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }
        
       }
       catch (SQLException e) {
        System.out.println(e.toString());
       } catch (Exception e) {
        System.out.println(e.toString());
       }
       return false;
    }

    public boolean fetchnotconfirmed(int user_id, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String confirm = "NO";
        String query = "select count(stud_id) as number from visits where stud_id='"+user_id+"' and supervisor_confirm='"+confirm+"'";
        try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet result = st.executeQuery(query);
        HttpSession session = request.getSession();

        // 6. Process the results
        if (result.next()) {
            int notconfirm_number = result.getInt("number");
            session.setAttribute("notconfirm_number", notconfirm_number);
    
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }else{
            int notconfirm_number = 0;
            session.setAttribute("notconfirm_number", notconfirm_number);
            // 7. Close all connections
            st.close();
            con.close();
            return true;
        }

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
        String go = "Welcome!";
        String dont = "Something went wrong during fetching of data";

        if(fetchplacement(user_id, request, response)&&fetchreports(user_id, request, response)&&fetchconfirmed(user_id, request, response)&&fetchnotconfirmed(user_id, request, response)){
            request.setAttribute("go", go);
            request.getRequestDispatcher("Student/studhome.jsp").forward(request, response);
        }else if(fetchplacement(user_id, request, response)||fetchreports(user_id, request, response)||fetchconfirmed(user_id, request, response)||fetchnotconfirmed(user_id, request, response)){
            request.setAttribute("go", go);
            request.getRequestDispatcher("Student/studhome.jsp").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("Student/studhome.jsp").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}