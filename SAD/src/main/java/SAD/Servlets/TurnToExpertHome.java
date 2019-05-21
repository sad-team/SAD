package SAD.Servlets;

import SAD.Database.DataOperation;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mchange.io.FileUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class TurnToExpertHome extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String expertName = request.getParameter("expertName");

        DataOperation dataoperator = DataOperation.getOperator();

        String pact = "../../expertInfo.json";
        String curDir = this.getClass().getClassLoader().getResource("").getPath();
        String pactFile = curDir+pact;
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(pactFile);
        if (!myFile.exists()) {
            response.getWriter().write("Can't Find " + pactFile);
        }
        try {
            FileInputStream fis = new FileInputStream(pactFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);

            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);  //new String(str,"UTF-8")
            }
            in.close();

            JSONObject jstr = JSONObject.parseObject(strbuffer.toString());

           JSONObject detail = jstr.getJSONObject(expertName);
            request.getRequestDispatcher("/expertHome.jsp?&expertName="+expertName+"&detail="+detail.toJSONString()).forward(request, response);

        } catch (IOException e) {
            e.getStackTrace();
            response.getWriter().write("can't find!");
        }


    }

    @Test
    public void testInput() {

        String pactFile = "/Users/mac/Downloads/apache-tomcat-9.0.17/webapps/SAD/src/main/java/SAD/Servlets/expertInfo.json";
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(pactFile);
        if (!myFile.exists()) {
            System.err.println("Can't Find " + pactFile);
        }
        try {
            FileInputStream fis = new FileInputStream(pactFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);

            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);  //new String(str,"UTF-8")
            }
            in.close();
            System.out.println(strbuffer);
        } catch (IOException e) {
            e.getStackTrace();
        }


    }

    @Test
    public void testPath() {

        String pactFile = "expertInfo.json";
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(pactFile);
        if (!myFile.exists()) {
            System.err.println("Can't Find " + pactFile);
        }
        try {
            FileInputStream fis = new FileInputStream(pactFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);

            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);  //new String(str,"UTF-8")
            }
            in.close();

            System.out.println(strbuffer);
        } catch (IOException e) {
            e.getStackTrace();
        }


    }
}