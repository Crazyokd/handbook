package pkg.handbook.service.impl;

import java.util.List;

import pkg.handbook.dao.BillDao;
import pkg.handbook.dao.UserDao;
import pkg.handbook.domain.Bill;
import pkg.handbook.domain.User;
import pkg.handbook.service.BusinessService;
import pkg.handbook.utils.DaoFactory;

public class BusinessServiceImpl implements BusinessService{
    private BillDao billDao = DaoFactory.getInstance().createDao("pkg.handbook.dao.impl.BillDaoImpl", BillDao.class);
	private UserDao userDao = DaoFactory.getInstance().createDao("pkg.handbook.dao.impl.UserDaoImpl", UserDao.class);

	
	public boolean registerUser(String username, String password) {
        if (isRegister(username))return false;
		userDao.add(username, password);
        return true;
	}

	public User loginUser(String username, String password){
        
		return userDao.search(username, password);
	}

	public boolean isRegister(String username){
		return userDao.search(username) != null;
	}    



    public void writeBill(List<Bill> bills, String user_name){
		billDao.writeData(bills, user_name);
	}

	public List<Bill> readBill(String user_name){
		return billDao.readData(user_name);
	}
}
