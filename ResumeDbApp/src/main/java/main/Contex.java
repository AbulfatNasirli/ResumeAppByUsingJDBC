/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.CountryDaoInter;
import dao.EmploymentDaoInter;
import dao.SkillDaoInter;
import dao.UserDaoInter;
import dao.UserSkillDaoInter;
import dao.impl.CountryDaoImpl;
import dao.impl.EmploymentDaoImpl;
import dao.impl.SkillDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.UserSkillDaoImpl;

/**
 *
 * @author DELL
 */
public class Contex {
     public static UserDaoInter instaceUserdao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoInter instaceUserSkilldao() {
        return new UserSkillDaoImpl();
    }

    public static EmploymentDaoInter instaceEmploymentdao() {
        return new EmploymentDaoImpl();
    }

    public static SkillDaoInter instanceSkilldao() {
        return new SkillDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }
}
