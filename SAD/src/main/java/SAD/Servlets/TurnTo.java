package SAD.Servlets;

import SAD.Database.DataOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TurnTo extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String target = (String)req.getParameter("target");
        String userName = (String)req.getParameter("userName");
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        DataOperation dataoperator = (DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId(userName);
        int role = dataoperator.selectUserRole(id);


        try {
            req.getRequestDispatcher("/"+target+"?userName="+userName+"&role="+role).forward(req, resp);
        } catch(Exception e){
            // resp.sendRedirect(req.getContextPath() + "/htmls/index.html");
        }
    }
}
