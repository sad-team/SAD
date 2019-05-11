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

public class Login extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String userName = (String)req.getParameter("signInName");
        String passwd = (String)req.getParameter("signInPasswd");

        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        DataOperation dataoperator = (DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId(userName);
        int permit = dataoperator.userAuthority(id,passwd);

        try {
            switch (permit){
                case -1:
                case -2:
                    req.getRequestDispatcher("/login.jsp?status=login Error! username or password error!").forward(req, resp);
                    break;
                case 0:
                    int role = dataoperator.selectUserRole(id);
                    //这里要区分专家和用户
                    req.getRequestDispatcher("/index.jsp?name="+userName).forward(req, resp);
                    break;
                    default:
                        break;
            }
        } catch(Exception e){
           // resp.sendRedirect(req.getContextPath() + "/htmls/index.html");
        }
    }
}
