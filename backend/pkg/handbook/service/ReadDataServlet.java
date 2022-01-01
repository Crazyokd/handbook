package pkg.handbook.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkg.handbook.service.impl.BusinessServiceImpl;

public class ReadDataServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        BusinessService service = new BusinessServiceImpl();
        PrintWriter out = response.getWriter();

        var bills = service.readBill(username);

        for (var bill : bills) {
            out.write("money=" + bill.getMoney() + "&");
            out.write("label=" + bill.getLabel() + "&");
            out.write("comment=" + bill.getComment() + "&");
            out.write("calendar=" + bill.getCalendar() + "&");
            out.write("ctgr=" + bill.getCtgr() + ";");
        }
        
        out.close();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request, response);
    }
}