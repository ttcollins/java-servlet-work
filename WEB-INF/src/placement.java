package NAD;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class placement extends HttpServlet{
    public boolean fill(String field_email, String field_fname, String field_other, String field_number, int user_id, String start_date, String end_date, String org_name, String org_address, String additional_address_info, String org_contact, String org_email){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "insert into placement (field_email, field_fname, field_other, field_number, user_id, start_date, end_date, org_name, org_address, additional_address_info, org_contact, org_email) VALUES('"+field_email+"', '"+field_fname+"', '"+field_other+"', '"+field_number+"', '"+user_id+"', '"+start_date+"', '"+end_date+"', '"+org_name+"', '"+org_address+"', '"+additional_address_info+"', '"+org_contact+"', '"+org_email+"')";
        String query1 = "select * from placement where user_id='" + user_id + "'";
      try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        int rs = st.executeUpdate(query);
        ResultSet result = st.executeQuery(query1);
        if(result.next()){
            int placement_id = result.getInt("ID");
            String query2 = "update student set placement_id='"+ placement_id +"' where user_id='"+ user_id +"'";
            int resultant = st.executeUpdate(query2);
        }
        
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

    public boolean check(int user_id, HttpServletRequest request, HttpServletResponse response) {
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
        ResultSet rs = st.executeQuery(query);
        
        // 6. Process the results
        if (rs.next()) {
         // 7. Close all connections
         HttpSession session = request.getSession();
         String field_email = rs.getString("field_email");
         String field_fname = rs.getString("field_fname");
         String field_other = rs.getString("field_other");
         String field_number = rs.getString("field_number");
         String start_date = rs.getString("start_date");
         String end_date = rs.getString("end_date");
         String org_name = rs.getString("org_name");
         String org_address = rs.getString("org_address");
         String additional_address_info = rs.getString("additional_address_info");
         String org_contact = rs.getString("org_contact");
         String org_email = rs.getString("org_email");
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
         request.getRequestDispatcher("Student/studplacementedit.jsp").forward(request, response);

         rs.close();
         st.close();
         con.close();
         return true;
        }else{
            response.sendRedirect("Student/studplacement.jsp");
        }        
        // 7. Close all connections
        rs.close();
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
        int user_id = (int) session.getAttribute("ID");

        check(user_id, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        HttpSession session = request.getSession();
        String field_email = request.getParameter("field_email");
        String field_fname = request.getParameter("field_supervisor_fname");
        String field_other = request.getParameter("field_supervisor_other");
        String field_number = request.getParameter("phonenumber");
        int user_id = (int) session.getAttribute("ID");
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        String org_name = request.getParameter("organisation");
        String org_address = request.getParameter("address");
        String additional_address_info = request.getParameter("additional_information");
        String org_contact = request.getParameter("contact");
        String org_email = request.getParameter("email");
        String placementgo = "Your details have been saved";
        String placementdont = "Something went wrong, Please try again!";

        if(fill(field_email, field_fname, field_other, field_number, user_id, start_date, end_date, org_name, org_address, additional_address_info, org_contact, org_email)){
            request.setAttribute("placementgo", placementgo);
            request.getRequestDispatcher("studhome").forward(request, response);
        }else{
            request.setAttribute("placementdont", placementdont);
            request.getRequestDispatcher("Student/studplacement.jsp").forward(request, response);
        }
    }
}
