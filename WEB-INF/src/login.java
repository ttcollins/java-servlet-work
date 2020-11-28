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

public class login extends HttpServlet{
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

    public boolean check(String email, String pass, HttpServletRequest request, HttpServletResponse response) {
        // 2. Define the Connection URL
        String url = "jdbc:mysql://localhost/NAD";
      
        String dbUsername = "root";
        String dbPassword = "";
        String query = "select * from users where email='" + email + "' and password='" + pass + "'";
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
                String fname = rs.getString("fname");
                int ID = rs.getInt("ID");
                HttpSession session = request.getSession();
                session.setAttribute("fname", fname);
                session.setAttribute("ID", ID);
                int role = rs.getInt("role");
                int stud = 3;
                int sup = 2;
                int hod = 1;
                if(role==stud){
                    session.setAttribute("email", email);
                    response.sendRedirect("studhome");
                }else if(role==sup){
                    session.setAttribute("email", email);
                    response.sendRedirect("suphome");
                }else if(role==hod){
                    session.setAttribute("email", email);
                    response.sendRedirect("hodhome");
                }else{
                    String error = "Your credentials do not match our records...";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

                rs.close();
                st.close();
                con.close();
                return true;
            }else{
                String error = "Your credentials do not match our records...";
                request.setAttribute("error", error);
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String pass1 = request.getParameter("pass");

        //encryption
        final String secretKey = "secrete";

        String originalString = pass1;

        login cmp = new login();
        String encryptedString = cmp.encrypt(originalString, secretKey);
        //String decryptedString = aesEncryptionDecryption.decrypt(encryptedString, secretKey);
        String pass = encryptedString;

        check(email, pass, request, response);
        /*if(check(email, pass, request)){
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("studhome.jsp");
        }else{
            String error = "Your credentials do not match our records...";
            request.setAttribute("error", error);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }*/
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}