package examples;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/updatestud")
public class updatestud extends HttpServlet{
    public boolean update(String fname, String other, String std_number, String reg_number, String course, String gender, String number, String email, String password, int user_id){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "update users set fname='"+ fname +"', other='"+ other +"', gender='"+ gender +"', number='"+ number +"', email='"+ email+"', password='"+ password +"' where ID='"+ user_id +"'";
        String query1 = "update student set std_number='"+ std_number +"', reg_number='"+ reg_number+"', course='"+ course+"' where user_id='"+ user_id +"'";
      try {
        //1. Loading the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // 3. Establish the connection
        Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
        // 4. Create a statement
        Statement st = con.createStatement();
        
        // 5. Execute a Query
        int rs = st.executeUpdate(query);
        int rs1 = st.executeUpdate(query1);
        
        // 6. Process the results
        if (rs!=0 && rs1!=0) {
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
        String fname = request.getParameter("fname");
        String other = request.getParameter("other");
        String std_number = request.getParameter("std_number");
        String reg_number = request.getParameter("reg_number");
        String course = request.getParameter("course");
        String gender = request.getParameter("gender");
        String number = request.getParameter("number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int user_id = (int) session.getAttribute("user_id");
        String go = "Your Details have been saved!";
        String dont = "Something went wrong, please try again!";

        if(update(fname, other, std_number, reg_number, course, gender, number, email, password, user_id)){
            session.setAttribute("email", email);
            session.setAttribute("fname", fname);
            request.setAttribute("go", go);
            request.getRequestDispatcher("updatestudent").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("updatestud.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}
