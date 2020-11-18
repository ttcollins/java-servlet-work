package NAD;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class giverole extends HttpServlet{
    public boolean fetch(int ID, HttpServletRequest request, HttpServletResponse response){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from users where ID='" + ID + "'";
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

                HttpSession session = request.getSession();
                request.setAttribute("fname", fname);
                request.setAttribute("other", other);
                request.setAttribute("gender", gender);
                request.setAttribute("number", number);
                request.setAttribute("email", email);
                request.setAttribute("role", role);
                request.setAttribute("user_id", user_id);
                request.getRequestDispatcher("HOD/giverole.jsp").forward(request, response);
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

    public boolean update(String fname, String other, String gender, String number, String email, int role, int user_id){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "update users set fname='"+ fname +"', other='"+ other +"', gender='"+ gender +"', number='"+ number +"', email='"+ email+"', role='"+ role +"' where ID='"+ user_id +"'";
        String query1 = "insert into supervisor (user_id) VALUES('"+user_id+"')";
        try {
            //1. Loading the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // 3. Establish the connection
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            // 4. Create a statement
            Statement st = con.createStatement();
        
            // 5. Execute a Query
            int rs = st.executeUpdate(query);
            int result = st.executeUpdate(query1);

            // 6. Process the results
            if (rs!=0 && result!=0) {
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
        int ID = Integer.parseInt(request.getParameter("ID"));
        /*response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuffer buf = new StringBuffer();
        buf.append("<html><body><h1>ID is "+ID+"</h1></body></html>");
        out.println(buf.toString());
        out.close();*/
        fetch(ID, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        HttpSession session = request.getSession();
        String fname = request.getParameter("fname");
        String other = request.getParameter("other");
        String gender = request.getParameter("gender");
        String number = request.getParameter("number");
        String email = request.getParameter("email");
        int role = Integer.parseInt(request.getParameter("role"));
        int user_id = Integer.parseInt(request.getParameter("ID"));
        String go = "Details have been saved!";
        String dont = "Something went wrong, please try again!";

        if(update(fname, other, gender, number, email, role, user_id)){
            request.setAttribute("go", go);
            request.getRequestDispatcher("hodhome").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("hodhome").forward(request, response);
        } 
   }
}
