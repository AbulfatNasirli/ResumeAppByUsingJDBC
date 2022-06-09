/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.AbstractDAO;
import dao.CountryDaoInter;
import entity.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
      @Override
    public Country getCountry(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nationality = rs.getString("nationality");
        String name = rs.getString("name");
        return new Country(id, nationality, name);
    }

    @Override
    public List<Country> getAllCountry() throws SQLException, ClassNotFoundException {
        List<Country> result = new ArrayList<>();
        Country country = null;
        Connection c = connection();
        PreparedStatement stmt = c.prepareStatement("SELECT*FROM COUNTRY");
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            country = getCountry(rs);
            result.add(country);
        }
        return result;
    }

    @Override
    public boolean addCountry(Country country) {
        try {
            Connection c = connection();
            PreparedStatement stmt = c.prepareStatement("INSERT INTO COUNTRY(ID,NATIONALITY,NAME) VALUES(?,?,?)");
            stmt.setInt(1, country.getId());
            stmt.setString(2, country.getNationality());
            stmt.setString(3, country.getName());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCountry(Country country) {
        try {
            Connection c = connection();
            PreparedStatement stmt = c.prepareStatement("update country set nationality=?,name=? where id=? ");
            stmt.setString(1, country.getNationality());
            stmt.setString(2, country.getName());
            stmt.setInt(3, country.getId());
           return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public Country getCountryById(int countryId) throws SQLException, ClassNotFoundException {
        Country country = null;
        Connection c = connection();
        PreparedStatement stmt = c.prepareStatement("select*from Country where id = " + countryId);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            country = getCountry(rs);
        }
        return country;
    }
    
}
