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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        DataOperation dataoperator = DataOperation.getOperator();
        int id = Integer.parseInt((String)session.getAttribute("id"));
        dataoperator.changeEmail(id,email);
        dataoperator.changeUserPhone(id,phoneNumber);
        request.getRequestDispatcher("/index.jsp?").forward(request, response);
    }
}
