/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Skill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface SkillDaoInter {
     public Skill getSkill(ResultSet rs) throws Exception;

    public List<Skill> getAllSkill() throws Exception;

    public boolean updateSkill(Skill skill) throws SQLException, ClassNotFoundException;

    public boolean insertSkill(Skill skill);

    public Skill getSkillById(int skillId) throws Exception;
}
