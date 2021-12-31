package pkg.handbook.dao;

import pkg.handbook.domain.User;

public interface UserDao{
    public void add(String username, String password);

    public User search(String username);

    public User search(String username, String password);
}