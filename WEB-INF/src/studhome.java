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

    public boolean assignsup(HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select user_id, MIN(number_of_students) as stud_pop from supervisor";
        try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet result = st.executeQuery(query);

        // 6. Process the results
        if (result.next()) {
            int supervisor_id = result.getInt("user_id");
            int stud_number = result.getInt("stud_pop");
            HttpSession session = request.getSession();
            session.setAttribute("supervisor_id", supervisor_id);
            String query1 = "update student set supervisor_id='"+ supervisor_id +"' where supervisor_id is null";
            int rs = st.executeUpdate(query1);
            st.close();
            con.close();
            return true;
            // 7. Close all connections
        }

       }
       catch (SQLException e) {
        System.out.println(e.toString());
       } catch (Exception e) {
        System.out.println(e.toString());
       }
       return false;
    }

    public boolean fetchsup(HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        HttpSession session = request.getSession();
        int supervisor_id = (int)session.getAttribute("supervisor_id");
        String query = "select * from users where id='"+supervisor_id+"'";
        try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        ResultSet result = st.executeQuery(query);

        // 6. Process the results
        if (result.next()) {
            String sup_fname = result.getString("fname");
            String sup_other = result.getString("other");
            String sup_gender = result.getString("gender");
            String sup_number = result.getString("number");
            String sup_email = result.getString("email");
            session.setAttribute("sup_fname", sup_fname);
            session.setAttribute("sup_other", sup_other);
            session.setAttribute("sup_gender", sup_gender);
            session.setAttribute("sup_number", sup_number);
            session.setAttribute("sup_email", sup_email);
            st.close();
            con.close();
            return true;
            // 7. Close all connections
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

        assignsup(request, response);
        fetchsup(request, response);

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