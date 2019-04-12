package SAD.Try;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class tryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/xml; charset=UTF-8");
            BufferedReader reader = req.getReader();
            StringBuilder rawstring = new StringBuilder();
            String inputline;
            while ((inputline = reader.readLine()) != null) {
                rawstring.append(inputline);
            }
            Map<String, String> post_key_value = JSON.parseObject(rawstring.toString(), Map.class);
            PrintWriter writer = resp.getWriter();
            writer.print(post_key_value);
        }catch(Exception e){
            return;
        }
    }
}
