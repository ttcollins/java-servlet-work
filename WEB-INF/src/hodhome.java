package NAD;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class hodhome extends HttpServlet{
    public boolean fetchsups(HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from users where role is null";
        try {
            //1. Loading the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // 3. Establish the connection
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            // 4. Create a statement
            Statement st = con.createStatement();
        
            // 5. Execute a Query
            ResultSet rs = st.executeQuery(query);
            List dataList = new ArrayList();
        
            // 6. Process the results
            while (rs.next()) {
                dataList.add(rs.getString("fname"));
                dataList.add(rs.getString("other"));
                dataList.add(rs.getString("gender"));
                dataList.add(rs.getString("number"));
                dataList.add(rs.getString("email"));
                dataList.add(rs.getInt("role"));
                dataList.add(rs.getInt("ID"));
            }
        
            // 7. Close all connections
            st.close();
            con.close();
            request.setAttribute("data", dataList);
            request.getRequestDispatcher("HOD/hodhome.jsp").forward(request, response);
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
        String go = "Your details have been saved";
        String dont = "Something went wrong, Data failed to display!";

        if(!fetchsups(request, response)){
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("hodhome").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}