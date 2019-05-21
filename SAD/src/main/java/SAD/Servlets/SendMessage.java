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
import java.util.Date;
import java.text.SimpleDateFormat;

public class SendMessage extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String toUser = request.getParameter("toUser");
        String content = request.getParameter("content");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String sendTime = df.format(new Date());

        DataOperation dataoperator = DataOperation.getOperator();
        int id = Integer.parseInt((String)session.getAttribute("id"));
        int toId =dataoperator.getUserId(toUser);
        int state = dataoperator.sendMessage(id,toId,content,sendTime);

        try {
            if (state==0){
                response.getWriter().write("发送成功");
            } else {
                response.getWriter().write("发送失败");
        }


         //   request.getRequestDispatcher("/expertHome.jsp?name="+userName).forward(request, response);

        } catch(Exception e){
            // response.sendRedirect(request.getContextPath() + "/htmls/index.html");
        }
    }

    @Test
    public void printTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }
}
