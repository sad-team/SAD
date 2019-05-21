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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        DataOperation dataoperator = DataOperation.getOperator();
        int id = Integer.parseInt((String)session.getAttribute("id"));

        List<Map<String,Object>> messages = dataoperator.getMessage(id);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(messages));
        String tmp = array.toJSONString();

        try {

            request.getRequestDispatcher("/myMessage.jsp?messages=" + tmp).forward(request, response);
        }catch(Exception e){

        }


    }

}
