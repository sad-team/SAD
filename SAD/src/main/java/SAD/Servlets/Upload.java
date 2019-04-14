package SAD.Servlets;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;

@MultipartConfig(maxFileSize = 1024*1024*512, maxRequestSize = 1024*1024*1024)
public class Upload extends HttpServlet {

}
