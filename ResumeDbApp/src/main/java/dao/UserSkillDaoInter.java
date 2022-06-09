/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.UserSkill;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface UserSkillDaoInter {

    public List<UserSkill> getAllSkillByUserId(int userId);

    public boolean deleteUserSkill(int id);

    public boolean addUserSkill(UserSkill userSkill);
    
    public boolean updateUserSkill(UserSkill userSkill);
}
