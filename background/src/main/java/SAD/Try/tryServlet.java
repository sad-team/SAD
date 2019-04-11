package SAD.Try;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class tryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("GBK");
        resp.setContentType("application/xml; charset=GBK");
        BufferedReader reader=req.getReader();
        StringBuilder rawstring=new StringBuilder();
        String inputline;
        while((inputline=reader.readLine())!=null){
            rawstring.append(inputline);
        }
        Map<String,String> post_key_value= JSON.parseObject(rawstring.toString(),Map.class);
    }
}
