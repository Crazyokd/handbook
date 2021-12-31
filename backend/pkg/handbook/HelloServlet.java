package pkg.handbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.write("<html><head><title>jw_vsc</title><meta charset='UTF-8'/></head>");
        out.write("<body><h1 align='center'>Congratulations on your successful configuration!</h1></body></html>");
        out.close();
    }
}