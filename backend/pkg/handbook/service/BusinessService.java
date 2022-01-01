package pkg.handbook.service;

import java.util.List;

import pkg.handbook.domain.Bill;
import pkg.handbook.domain.User;

public interface BusinessService {
    public boolean registerUser(String username, String password);

    public User loginUser(String username, String password);

    public void writeBill(List<Bill> bills, String user_name);

    public List<Bill> readBill(String user_name);
}
