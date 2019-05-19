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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String userName = (String) req.getParameter("userName");
        String expertName = req.getParameter("expertName");


        String pact = "../../expertInfo.json";
        String curDir = this.getClass().getClassLoader().getResource("").getPath();
        String pactFile = curDir+pact;
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(pactFile);
        if (!myFile.exists()) {
            resp.getWriter().write("Can't Find " + pactFile);
            System.err.println("Can't Find " + pactFile);
        }
        try {
            resp.getWriter().write("trying");
            resp.getWriter().println("curdir:"+curDir);
            FileInputStream fis = new FileInputStream(pactFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);

            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);  //new String(str,"UTF-8")
            }
            in.close();
            resp.getWriter().write(strbuffer.toString());
            JSONObject jstr = JSONObject.parseObject(strbuffer.toString());
            resp.getWriter().write("jstr:"+jstr);
            // 获取前端传来的expertName为key,返回value
            JSONObject value1 = jstr.getJSONObject("谭火彬");
            JSONObject value2 = jstr.getJSONObject("林广艳");
            JSONObject value3 = jstr.getJSONObject("杜孝平");
            resp.getWriter().write("value1"+value1);
            resp.getWriter().write("value2"+value2);
            resp.getWriter().write("value3"+value3);

        } catch (IOException e) {
            e.getStackTrace();
            resp.getWriter().write("can't find!");
        }


    }

    @Test
    public void testInput() {

        String pactFile = "/Users/mac/Downloads/apache-tomcat-9.0.17/webapps/SAD/src/main/java/SAD/Servlets/expertInfo.json";
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(pactFile);//"D:"+File.separatorChar+"DStores.json"
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