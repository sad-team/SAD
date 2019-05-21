package SAD.Servlets;

import SAD.Database.DataOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Follow extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String followName = request.getParameter("expertName");
        DataOperation dataoperator = DataOperation.getOperator();
        int id = Integer.parseInt((String)session.getAttribute("id"));
        int followId = dataoperator.getUserId(followName);
        int followState =  dataoperator.followState(id,followId);

        if (followState==0) {
            dataoperator.followUser(id,followId);
        } else {
            dataoperator.unFollowUser(id,followId);
        }

        String isFollowing = (followState==0) ? "取消关注" : "关注" ;

        try {
            request.getRequestDispatcher("/expertHome.jsp?&isFollowing="+isFollowing).forward(request, response);
        } catch(Exception e){
            // response.sendRedirect(request.getContextPath() + "/htmls/index.html");
        }
    }
}
