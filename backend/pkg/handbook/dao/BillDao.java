package pkg.handbook.dao;

import java.util.List;

import pkg.handbook.domain.Bill;

public interface BillDao{
    public List<Bill> readData(int user_id);
    public void writeData(List<Bill> bills);
}