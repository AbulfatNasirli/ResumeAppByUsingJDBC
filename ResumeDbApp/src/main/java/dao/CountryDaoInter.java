/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CountryDaoInter {
    public Country getCountry(ResultSet rs) throws SQLException;

    public List<Country> getAllCountry() throws SQLException, ClassNotFoundException;

    public boolean addCountry(Country country);

    public boolean updateCountry(Country country);

    public Country getCountryById(int id) throws SQLException, ClassNotFoundException;
}
