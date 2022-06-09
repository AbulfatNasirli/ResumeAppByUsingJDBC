/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface UserDaoInter {
    public User findByEmailAndPassword(String email,String password);
    
    public User findByEmail(String email);
    
    public List<User> getAll(String name,String surname);

    public boolean removeUser(int id);

    public boolean updateUser(User u) throws SQLException, ClassNotFoundException;

    public User getById(int id);

    public boolean addUser(User user);
}
