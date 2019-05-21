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

public class Search extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String searchType = (request.getParameter("searchType") == null)
                ? "" : request.getParameter("searchType");
        String searchWord = (request.getParameter("searchWord") == null)
                ? "" : request.getParameter("searchWord");

        String type="ALL";

        //问题确定出在 type 上
        //问题在于接收的searchType和searchWord不正确

        if(searchType.equals("1")){
            type="ALL";
        }else if(searchType.equals("2")){
            type="PAPER";
        }else if(searchType.equals("3")){
            type="PATENT";
        }else if(searchType.equals("4")){
            type="PROJECT";
        }

        DataOperation dataoperator = DataOperation.getOperator();

        try {
            List<Map<String,Object>> result = dataoperator.searchResource(searchWord,type);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(result));
            String tmp = array.toJSONString();
            request.getRequestDispatcher("/searchResults.jsp?resource="+tmp).forward(request, response);
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
