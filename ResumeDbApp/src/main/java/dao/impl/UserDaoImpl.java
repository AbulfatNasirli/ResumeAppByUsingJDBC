/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.AbstractDAO;
import dao.UserDaoInter;
import entity.Country;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        int age = rs.getInt("age");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String phone = rs.getString("phone");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_Id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birth_place");
        Date birthDate = rs.getDate("birthdate");
        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, age, email, password, phone, profileDesc, address, birthDate, birthplaceId, nationality, birthPlace);
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        int age = rs.getInt("age");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String phone = rs.getString("phone");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_Id");

        Date birthDate = rs.getDate("birthdate");
        User user = new User(id, name, surname, age, email, password, phone, profileDesc, address, birthDate, birthplaceId, null, null);
        user.setPassword(rs.getString("password"));
        return user;
    }

    @Override
    public List<User> getAll(String name, String surname) {
        List<User> result = new ArrayList<>();
        try ( Connection con = connection();) {
            String sql = "SELECT u.*, "
                    + " n.nationality,"
                    + " c.name AS birth_place "
                    + "  FROM user u "
                    + "   left JOIN country n on u.nationality_id=n.id"
                    + "    left JOIN country c on u.birthplace_id=c.id"
                    + " where 1=1 ";
            if (name != null && !name.trim().isEmpty()) {
                sql += " and u.name=? ";
            }
            if (surname != null && !surname.trim().isEmpty()) {
                sql += " and u.surname=? ";
            }

            PreparedStatement stmt = con.prepareStatement(sql);
            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean removeUser(int id) {
        try ( Connection con = connection();) {
            Statement stmt = con.createStatement();
            return stmt.execute("delete from user where id =" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean updateUser(User u) {
        try ( Connection con = connection();) {
            PreparedStatement pstm = con.prepareStatement("update user set name=?,surname=?,email=?,phone=?, profile_description=?,address=?,birthdate=?,birthplace_id=? where id=?");
            pstm.setString(1, u.getName());
            pstm.setString(2, u.getSurname());
            pstm.setString(3, u.getEmail());
            pstm.setString(4, u.getPhone());
            pstm.setString(5, u.getProfileDesc());
            pstm.setString(6, u.getAddress());
            pstm.setDate(7, u.getBirthDate());
            pstm.setInt(8, u.getBirthPlace().getId());
            pstm.setInt(9, u.getId());
            return pstm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try ( Connection con = connection();) {
            Statement stmt = con.createStatement();
            stmt.execute(" SELECT "
                    + " u.*, "
                    + " "
                    + " n.nationality AS nationality, "
                    + " "
                    + " c.NAME AS birth_place  "
                    + ""
                    + " FROM "
                    + " USER u "
                    + " "
                    + " LEFT JOIN country n ON u.nationality_id = n.id "
                    + ""
                    + " LEFT JOIN country c ON u.birthplace_id = c.id "
                    + " where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
      private BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        try ( Connection con = connection();) {
            PreparedStatement pstm = con.prepareStatement("insert into user (name,surname,email,password,phone,profile_description,address) values (?,?,?,?,?,?,?)");
            pstm.setString(1, u.getName());
            pstm.setString(2, u.getSurname());
            pstm.setString(3, u.getEmail());
            pstm.setString(4, crypt.hashToString(4, u.getPassword().toCharArray()));
            pstm.setString(5, u.getPhone());
            pstm.setString(6, u.getProfileDesc());
            pstm.setString(7, u.getAddress());
            return pstm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try ( Connection con = connection();) {
            PreparedStatement pstm = con.prepareStatement("select*from user where email=? and password=?");
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try ( Connection con = connection();) {
            PreparedStatement pstm = con.prepareStatement("select*from user where email=?");
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
