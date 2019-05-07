package SAD.Servlets;

import SAD.Database.DataOperation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        String username = (String)req.getParameter("signInName");
        String passwd = (String)req.getParameter("signInPasswd"); DataOperation dataoperator;
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator = (DataOperation) context.getBean("dataoperator");
        boolean permit = dataoperator.login(username, passwd);

        try {
            if (permit)
            {
                req.getRequestDispatcher("/index.jsp?name="+username).forward(req, resp);

                //然后需要跳转到登录后的页面
            }
            else //跳转到原来的页面
            {
                req.getRequestDispatcher("/login.jsp?status=login Error! username or password error!").forward(req, resp);
            }


                //req.getRequestDispatcher("/index.html").forward(req, resp);
             //   resp.getWriter().write(req.getContextPath());
              //  resp.sendRedirect(req.getContextPath() + "/jsps/index.jsp");
                //resp.getWriter().write("Login error!");
        } catch(Exception e){
           // resp.sendRedirect(req.getContextPath() + "/htmls/index.html");
        }
    }
}
