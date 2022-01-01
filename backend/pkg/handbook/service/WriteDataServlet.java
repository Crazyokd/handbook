package pkg.handbook.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkg.handbook.domain.Bill;
import pkg.handbook.service.impl.BusinessServiceImpl;

public class WriteDataServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        Bill bill = new Bill();

        bill.setMoney(Double.parseDouble(request.getParameter("money")));
        bill.setLabel(request.getParameter("label"));
        bill.setComment(request.getParameter("comment"));
        bill.setCalendar(request.getParameter("calendar"));
        bill.setCtgr(Integer.parseInt(request.getParameter("ctgr")));

        String username = request.getParameter("username");
        BusinessService service = new BusinessServiceImpl();

        var list = new ArrayList<Bill>();
        list.add(bill);

        service.writeBill(list, username);

        PrintWriter out = response.getWriter();
        out.write(request.getParameter("label"));
        out.close();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request, response);
    }
}