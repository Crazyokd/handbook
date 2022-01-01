package pkg.handbook.dao;

import java.util.List;

import pkg.handbook.domain.Bill;

public interface BillDao{
    public List<Bill> readData(String user_name);

    public void writeData(List<Bill> bills, String user_name);
}