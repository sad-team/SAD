package SAD.Servlets;
import SAD.Database.DataOperation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

public class MyMessage extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String userName = (String)req.getParameter("userName");

        DataOperation dataoperator;
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId(userName);

        List<Map<String,Object>> messages = dataoperator.getMessage(id);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(messages));
        String tmp = array.toJSONString();
        resp.getWriter().write(messages.toString());
        try {

            req.getRequestDispatcher("/myMessage.jsp?messages=" + tmp + "&userName="+userName).forward(req, resp);
        }catch(Exception e){

        }


    }

}
