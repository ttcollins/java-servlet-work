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

public class updatehod extends HttpServlet{
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
            request.getRequestDispatcher("HOD/updatehod.jsp").forward(request, response);
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
        String password1 = request.getParameter("password");
        int user_id = (int) session.getAttribute("ID");
        String go = "Your Details have been saved!";
        String dont = "Something went wrong, please try again!";

        //encryption
        final String secretKey = "secrete";

        String originalString = password1;

        updatehod hodupdate = new updatehod();
        String encryptedString = hodupdate.encrypt(originalString, secretKey);
        //String decryptedString = aesEncryptionDecryption.decrypt(encryptedString, secretKey);
        String password = encryptedString;

        if(update(fname, other, gender, number, email, password, user_id)){
            session.setAttribute("email", email);
            session.setAttribute("fname", fname);
            request.setAttribute("go", go);
            request.getRequestDispatcher("hodhome").forward(request, response);
        }else{
            request.setAttribute("dont", dont);
            request.getRequestDispatcher("hodhome").forward(request, response);
        } 
   }
}
