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
import java.util.List;
import java.util.Map;

public class Login extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

//
        session.setAttribute("userName","t");
        session.setAttribute("role",0);
        session.setAttribute("id",1);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

        //ApplicationContext
/*
        String userName = request.getParameter("signInName");
        String passwd = request.getParameter("signInPasswd");
response.getWriter().println("fuckyou");
response.getWriter().println("userName: "+userName);
        response.getWriter().println("passwd: "+passwd);

        response.getWriter().println("1!");
        DataOperation dataOperator;
        response.getWriter().println("2!");

        //ApplicationContext context=new FileSystemXmlApplicationContext("spring_config.xml");
        response.getWriter().println("3!");
        DataOperation dataoperator = DataOperation.getOperator();
        response.getWriter().println("4!");
        int id = dataOperator.getUserId(userName);
        response.getWriter().println("5!");
        response.getWriter().println("id:"+id);
        int permit = dataOperator.userAuthority(id,passwd);
        response.getWriter().println("6!");
        response.getWriter().println("permit:"+permit);
        //request.getRequestDispatcher("/index.").forward(request, response);

        try {

            response.getWriter().println("permit:"+permit);
            switch (permit){
                case -1:
                case -2:
                    request.getRequestDispatcher("/login.jsp?status=login Error! username or password error!").forward(request, response);
                    break;
                case 0:
                    int role = dataOperator.selectUserRole(id);
response.getWriter().write("userName: "+userName+" id: "+id+" role: "+role+" permit "+permit);
                    //这里要区分专家和用户
                    request.getSession().setAttribute("userName",userName);
                    request.getSession().setAttribute("role",role);
                    request.getSession().setAttribute("id",id);
                    response.getWriter().write("sessionAttributes:"+session.getAttribute("userName"));
                    session.setAttribute("userName",userName);
                    session.setAttribute("role",role);
                    session.setAttribute("id",id);
                    request.getRequestDispatcher("/index.jsp).forward(request, response);
                   //request.getRequestDispatcher("/index.html").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch(Exception e){

           e.printStackTrace();
           // response.sendRedirect(req.getContextPath() + "/htmls/index.html");
        }

*/
    }

    @Test
    public void testResult(){
        DataOperation dataoperator;
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId("沈艳霞");
        int role = dataoperator.selectUserRole(id);
        System.out.println(role);
    }

    @Test
    public void testLogin() {

        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        DataOperation dataoperator = (DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId("t");
        int permit = dataoperator.userAuthority(id,"332");
        System.out.println(permit);
    }

    @Test
    public void testGetContext() {

        ApplicationContext context=new FileSystemXmlApplicationContext("src/resources/spring_config.xml");

        DataOperation dataoperator = (DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId("t");
        int permit = dataoperator.userAuthority(id,"332");
        System.out.println(permit);
    }
}
