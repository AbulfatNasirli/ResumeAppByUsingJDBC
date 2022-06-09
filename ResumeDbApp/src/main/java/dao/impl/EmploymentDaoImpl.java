/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.AbstractDAO;
import static dao.AbstractDAO.connection;
import dao.EmploymentDaoInter;
import entity.EmploymentHistory;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class EmploymentDaoImpl extends AbstractDAO implements EmploymentDaoInter {
    public static EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_description");
        int userId = rs.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAllEmployeeByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection con = connection();) {
            PreparedStatement stmt = con.prepareStatement("SELECT*FROM EMPLOYMENT_HISTORY WHERE USER_ID=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                EmploymentHistory u = getEmploymentHistory(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
