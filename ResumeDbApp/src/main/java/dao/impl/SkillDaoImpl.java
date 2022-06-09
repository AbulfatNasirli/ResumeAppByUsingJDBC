/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.AbstractDAO;
import static dao.AbstractDAO.connection;
import dao.SkillDaoInter;
import entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Skill skill = new Skill(id, name);
        return skill;
    }

    @Override
    public List<Skill> getAllSkill() throws Exception {
        Connection c = connection();
        List<Skill> result = new ArrayList<>();
        PreparedStatement stmt = c.prepareStatement("SELECT*FROM SKILL");
        stmt.execute();
        ResultSet resultSet = stmt.getResultSet();
        while (resultSet.next()) {
            Skill ss = null;
            ss = getSkill(resultSet);
            result.add(ss);
        }
        return result;

    }

    @Override
    public boolean updateSkill(Skill skill) {
        try ( Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement("Update skill set name=? where id=?");
            stmt.setString(1, skill.getName());
            stmt.setInt(2, skill.getId());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertSkill(Skill skill) {
        try {
            Connection c = connection();
            PreparedStatement stmt = c.prepareStatement("insert into skill( id,name) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getResultSet();
            stmt.setInt(1, skill.getId());
            stmt.setString(2, skill.getName());
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public Skill getSkillById(int skillId) throws Exception {
        Connection c = connection();
        PreparedStatement stmt = c.prepareStatement("Select*from skill where id=" + skillId);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        Skill skill = null;
        while (rs.next()) {
            skill = getSkill(rs);
        }
        return skill;

    }
}
