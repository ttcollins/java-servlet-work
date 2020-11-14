package NAD;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class logout extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.removeAttribute("fname");
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        doGet(request, response);
    }
}