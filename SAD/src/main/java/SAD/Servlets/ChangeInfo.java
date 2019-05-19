package SAD.Servlets;
import SAD.Database.DataOperation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChangeInfo extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String userName = (String)req.getParameter("userName");
        String phoneNumber = (String)req.getParameter("phoneNumber");
        String email = (String)req.getParameter("email");

        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        DataOperation dataoperator = (DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId(userName);
        dataoperator.changeEmail(id,email);
        dataoperator.changeUserPhone(id,phoneNumber);
        req.getRequestDispatcher("/index.jsp?name="+userName).forward(req, resp);




    }
}
