package SAD.Servlets;

import SAD.Database.DataOperation;
import SAD.Test.DatabaseTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUp extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        String signUpName=request.getParameter("signUpName");
        String signUpPasswd=request.getParameter("signUpPasswd");
        String signUpEmail = request.getParameter("signUpEmail");
        String identification = request.getParameter("identification");
        String cellphoneNumber = request.getParameter("cellphoneNumber");
        DataOperation dataoperator;
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");

        DatabaseTest dt=new DatabaseTest();
        dt.newUserTest();
        try {
            response.getWriter().write(signUpName);
            response.getWriter().write(signUpPasswd);
            response.getWriter().write(signUpEmail);
            response.getWriter().write(identification);
            response.getWriter().write(cellphoneNumber);

            dataoperator.newUser(signUpName,identification,cellphoneNumber,signUpPasswd,signUpEmail);
        }catch(Exception e){

        }
    }
}

