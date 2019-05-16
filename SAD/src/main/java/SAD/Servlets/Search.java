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

        String userName = (request.getParameter("userName") == null)
                ? "" : request.getParameter("userName");
        String searchType = (request.getParameter("searchType") == null)
                ? "" : request.getParameter("searchType");
        String searchWord = (request.getParameter("searchWord") == null)
                ? "" : request.getParameter("searchWord");

        String type="ALL";
     //   Object type = request.getParameter("searchType");

      //  String searchType = request.getParameterValues("searchType")[0];
//问题确定出在 type 上
//问题在于接收的searchType和searchWord不正确
       // String searchType = "ALL";
     //   String searchWord = "try";
if(searchType.equals("1")){
    type="ALL";
}else if(searchType.equals("2")){
    type="PAPER";
}else if(searchType.equals("3")){
    type="PATENT";
}else if(searchType.equals("4")){
    type="PROJECT";
}
        response.getWriter().println(userName);
        response.getWriter().println(searchType);
        response.getWriter().println(searchType.length());
        response.getWriter().println(searchWord);
        response.getWriter().println(searchWord.length());
      //  resp.getWriter().write("hello!!!!!!");

        DataOperation dataoperator;
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_config.xml");
        dataoperator=(DataOperation) context.getBean("dataoperator");
       // int id = dataoperator.getUserId(userName);

       // response.getWriter().println("id:"+id);

        response.getWriter().println("BEFORE");
    //    List<Map<String,Object>> result = dataoperator.searchResource(searchWord,searchType);
     //   JSONArray array= JSONArray.parseArray(JSON.toJSONString(result));
      //  String tmp = array.toJSONString();
        response.getWriter().println("AFTER");



        try {
            //request.getRequestDispatcher("/searchResults.jsp?userName="+userName).forward(request, response);
           // resp.getWriter().println("tmp:");
           // resp.getWriter().write(tmp);
           // resp.getWriter().write("size");
           // resp.getWriter().write(array.size());
            List<Map<String,Object>> result = dataoperator.searchResource(searchWord,type);
            response.getWriter().write(result.toString());
           // List<Map<String,Object>> resource = dataoperator.showUserResource(id);
           // response.getWriter().write(resource.toString());
            response.getWriter().write("hello");
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(result));
            String tmp = array.toJSONString();
            request.getRequestDispatcher("/searchResults.jsp?userName="+userName+"&resource="+tmp).forward(request, response);
       //     request.getRequestDispatcher("/myResource.jsp?resource=" + tmp + "&userName="+userName).forward(request, response);
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
