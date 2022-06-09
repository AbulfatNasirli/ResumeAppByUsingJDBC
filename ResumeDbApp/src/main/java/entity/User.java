/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.List;

/**

 *
 * @author DELL
 */
public class User {

    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String phone;
    private String profileDesc;
    private String address;
    private Date birthDate;
    private int birthplaceId;
    private Country nationality;
    private Country birthPlace;
    private List<UserSkill> userSkills;

    public User(int userId) {
        this.id = id;
    }

    public User(int id, String name, String surname,int age, String email,String password, String phone, String profileDesc, String address, Date birthDate, int birthplaceId, Country nationality, Country birthPlace) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age=age;
        this.email = email;
        this.password=password;
        this.phone = phone;
        this.profileDesc = profileDesc;
        this.address = address;
        this.birthDate = birthDate;
        this.birthplaceId = birthplaceId;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
    }

    public int getBirthplaceId() {
        return birthplaceId;
    }

    public void setBirthplaceId(int birthplaceId) {
        this.birthplaceId = birthplaceId;
    }

    public List<UserSkill> getUserSkill() {
        return userSkill;
    }

    public void setUserSkill(List<UserSkill> userSkill) {
        this.userSkill = userSkill;
    }

    private List<UserSkill> userSkill;

    public User() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name +", age="+age+ ", surname=" + surname + ", email=" + email + ", profileDesc=" + profileDesc + ", phone=" + phone + ", birthDate=" + birthDate + ", nationality=" + nationality + ", birthPlace=" + birthPlace + ", userSkills=" + userSkills + ", userSkill=" + userSkill + '}';
    }

}
