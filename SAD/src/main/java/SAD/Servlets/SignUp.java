package SAD.Servlets;

import SAD.Database.DataOperation;
import SAD.Test.DatabaseTest;
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

        DataOperation dataoperator = DataOperation.getOperator();

        try {
           int res = dataoperator.newUser(signUpName,identification,cellphoneNumber,signUpPasswd,signUpEmail);
           switch (res){
               case -1:
                   request.getRequestDispatcher("/login.jsp?status=The name has existed!").forward(request, response);
                   break;
               case -3:
                   request.getRequestDispatcher("/login.jsp?status=Cellphone number has been registered!").forward(request, response);
                   break;
               case -2:
                   request.getRequestDispatcher("/login.jsp?status=ID Number has been registered!").forward(request, response);
                   break;
               case 0:
                   request.getRequestDispatcher("/login.jsp?status=Sign Up Success!").forward(request, response);

                   break;

                   default:
                       response.getWriter().write("注册成功！");
                       break;
           }
        }catch(Exception e){

        }
    }


}

