package SAD.Servlets;

import SAD.Database.DataOperation;
import SAD.Test.DatabaseTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignUp extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String signUpName=request.getParameter("signUpName");
        String signUpPasswd=request.getParameter("signUpPasswd");
        String signUpEmail = request.getParameter("signUpEmail");
        String identification = request.getParameter("identification");
        String cellphoneNumber = request.getParameter("cellphoneNumber");
        DataOperation dataoperator;
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");
        try {
            response.getWriter().write(signUpName+"<br>");
            response.getWriter().write(signUpPasswd+"<br>");
            response.getWriter().write(signUpEmail+"<br>");
            response.getWriter().write(identification+"<br>");
            response.getWriter().write(cellphoneNumber+"<br>");

           int res = dataoperator.newUser(signUpName,identification,cellphoneNumber,signUpPasswd,signUpEmail);
           switch (res){
               case -1:
                   response.getWriter().write("用户名已存在");
                   break;
               case -3:
                   response.getWriter().write("手机号已注册过");
                   break;
               case -2:
                   response.getWriter().write("身份证号码已注册过");
                   break;
               case 0:
                   request.getRequestDispatcher("/login.jsp?status=login Error! username or password error!").forward(request, response);

                   break;
                   default:
                       break;

           }
        }catch(Exception e){

        }
    }
}

