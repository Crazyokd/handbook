package pkg.handbook.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkg.handbook.domain.User;
import pkg.handbook.service.impl.BusinessServiceImpl;

public class LoginServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BusinessService service = new BusinessServiceImpl();
        
        User user = service.loginUser(username, password);
        PrintWriter out = response.getWriter();
        if(user != null){
            out.write("success");
        }else{
            out.write("fail");
        }
        out.close();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request, response);
    }
}