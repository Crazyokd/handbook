package pkg.handbook.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import pkg.handbook.dao.BillDao;
import pkg.handbook.domain.Bill;
import pkg.handbook.utils.JdbcUtils;

public class BillDaoImpl implements BillDao{
    public List<Bill> readData(int user_id){
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select `money`,`label`,`comment`,`calendar`,`ctgr` from bill where user_id = ?;";
        List<Bill> bills = null;
        try {
            bills = runner.query(JdbcUtils.getConnection(), sql, new BeanListHandler<>(Bill.class), user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
    public void writeData(List<Bill> bills){
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

        int user_id = bills.get(0).getUserid();
        //删除该用户上一次的“购物车”
        deleteData(user_id);
        
        //插入一张bill
        String sql = "insert into bill(`money`,`label`,`comment`,`calendar`,`ctgr`,`user_id`) values(?,?,?,?,?,?);";
        for(Bill bill : bills){
            Object params[] = {bill.getMoney(), bill.getLabel(), bill.getComment(), bill.getCalendar(), bill.getCtgr(), user_id};
		    try {
                runner.update(sql, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deleteData(int user_id){
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "delete from bill where user_id = ?;";
        try {
            runner.update(JdbcUtils.getConnection(), sql, user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
