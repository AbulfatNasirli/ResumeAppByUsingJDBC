/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.AbstractDAO;
import static dao.AbstractDAO.connection;
import dao.UserSkillDaoInter;
import entity.Skill;
import entity.User;
import entity.UserSkill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    public static UserSkill getUserSkill(ResultSet rs) throws SQLException {
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");
        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try ( Connection con = connection();) {
            PreparedStatement stmt = con.prepareStatement("SELECT u.*," +
"					us.id as user_skill_id," +
"					us.skill_id," +
"					s.`name` as skill_name," +
"					us.power FROM user_skill as us " +
"					LEFT JOIN `user` as u ON us.user_id=u.id " +
"					LEFT JOIN skill as s ON us.skill_id=s.id " +
"					where us.user_id="+userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteUserSkill(int userSkillId) {
        try {
            Connection c = connection();
            PreparedStatement stmt = c.prepareStatement("delete from user_skill where id=?");
            stmt.setInt(1, userSkillId);
            System.out.println("id :"+String.valueOf(userSkillId));
            return stmt.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean addUserSkill(UserSkill userSkill) {
        try {
            Connection c = connection();
            PreparedStatement stmt = c.prepareStatement("insert into user_skill (user_id,skill_id,power) values(?,?,?)");
            stmt.setInt(1, userSkill.getUser().getId());
            stmt.setInt(2, userSkill.getSkill().getId());
            stmt.setInt(3, userSkill.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateUserSkill(UserSkill userSkill) {
        return false;
    }
}
