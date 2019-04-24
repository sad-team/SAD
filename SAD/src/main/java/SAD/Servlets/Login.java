package SAD.Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session=req.getSession();
        String username=req.getParameter("username");
        String passwd=req.getParameter("passwd");
        try {
            resp.getWriter().write(username + "   " + passwd);
        }catch(Exception e){

        }
    }
}
