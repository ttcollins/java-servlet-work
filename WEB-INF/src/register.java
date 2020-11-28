package NAD;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

//encryption libraries
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class register extends HttpServlet{
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";

    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public boolean registernormal(String fname, String other, String gender, String number, String email, String password) {
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "insert into users (fname, other, gender, number, email, password) VALUES('"+fname+"', '"+other+"', '"+gender+"', '"+number+"', '"+email+"', '"+password+"')";
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

    public boolean registerstud(String fname, String other, int role, String std_number, String reg_number, String course, String gender, String number, String email, String password){
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "insert into users (fname, other, role, gender, number, email, password) VALUES('"+fname+"', '"+other+"', '"+role+"', '"+gender+"', '"+number+"', '"+email+"', '"+password+"')";
        String query1 = "select * from users where email='" + email + "' and password='" + password + "'";
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
                int user_id = result.getInt("ID");
                String query2 = "insert into student (std_number, reg_number, course, user_id) VALUES('"+std_number+"', '"+reg_number+"', '"+course+"', '"+user_id+"')";
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
    

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String fname = request.getParameter("fname");
        String other = request.getParameter("other");
        String user_type = request.getParameter("user_type");
        String std_number = request.getParameter("std_number");
        String reg_number = request.getParameter("reg_number");
        String course = request.getParameter("course");
        String gender = request.getParameter("gender");
        String number = request.getParameter("number");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password");
        String go = "Your details have been saved, Please login!";
        String dont = "Something went wrong, please try again!";
        String type1 = "Student";
        String type2 = "Faculty Member";

        //encryption
        final String secretKey = "secrete";

        String originalString = password1;

        register reg = new register();
        String encryptedString = reg.encrypt(originalString, secretKey);
        //String decryptedString = aesEncryptionDecryption.decrypt(encryptedString, secretKey);
        String password = encryptedString;

        if(user_type.equals(type1)){
            int role = 3;
            if(registerstud(fname, other, role, std_number, reg_number, course, gender, number, email, password)){
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("fname", fname);
                request.setAttribute("go", go);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                HttpSession session = request.getSession();
                request.setAttribute("dont", dont);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }else if(user_type.equals(type2)){
            if(registernormal(fname, other, gender, number, email, password)){
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("fname", fname);
                request.setAttribute("go", go);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                HttpSession session = request.getSession();
                request.setAttribute("dont", dont);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}

