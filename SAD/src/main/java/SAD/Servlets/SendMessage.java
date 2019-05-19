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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session=req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String userName = req.getParameter("userName");
        String toUser = req.getParameter("toUser");
        String content = req.getParameter("content");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String sendTime = df.format(new Date());

        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        DataOperation dataoperator = (DataOperation) context.getBean("dataoperator");
        int id = dataoperator.getUserId(userName);
        int toId =dataoperator.getUserId(toUser);
        int state = dataoperator.sendMessage(id,toId,content,sendTime);

        try {
if (state==0){
    resp.getWriter().write("发送成功");
} else {
    resp.getWriter().write("发送失败");
}


         //   req.getRequestDispatcher("/expertHome.jsp?name="+userName).forward(req, resp);

        } catch(Exception e){
            // resp.sendRedirect(req.getContextPath() + "/htmls/index.html");
        }
    }

    @Test
    public void printTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }
}
