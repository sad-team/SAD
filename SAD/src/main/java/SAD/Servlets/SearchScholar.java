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

public class SearchScholar extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String searchWord = (request.getParameter("searchWord") == null)
                ? "" : request.getParameter("searchWord");

        DataOperation dataoperator = DataOperation.getOperator();
        int scholarId = dataoperator.getUserId(searchWord);

        try {
            if (scholarId == -1) {
                response.getWriter().write("专家不存在");
            } else {
                int expertRole = dataoperator.selectUserRole(scholarId);
                if (expertRole != 1) {
                    response.getWriter().write("专家不存在");
                } else {
                    List<Map<String,Object>> info = dataoperator.getUserInfo(scholarId);
                    String email = info.get(0).get("email").toString();
                    request.getRequestDispatcher("/searchScholar.jsp?expertName="+searchWord+"&scholarId="+scholarId+"&email="+email).forward(request, response);
                }
            }
        }catch(Exception e){

        }

    }

    @Test
    public void testResult(){
        DataOperation dataoperator;
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");
        String searchWord="try";
        String searchType="ALL";
        List<Map<String,Object>> result = dataoperator.searchResource(searchWord,searchType);
        System.out.println(result.toString());
    }
}
