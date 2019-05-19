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

public class QueryUserInfo extends HttpServlet{

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String userName = (String) req.getParameter("userName");
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        DataOperation dataoperator = (DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId(userName);
        List<Map<String,Object>> info = dataoperator.getUserInfo(id);
        String phoneNumber = info.get(0).get("cellphoneNumber").toString();
        String email = info.get(0).get("email").toString();

        req.getRequestDispatcher("/userInfo.jsp?userName=" + userName + "&phoneNumber="+phoneNumber+"&email="+email).forward(req, resp);



    }
}
