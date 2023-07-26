package com.tctm.practiceapp.repository;

import com.tctm.practiceapp.entity.ManagerEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ManagerRepository {

    @Select("SELECT * FROM managers")
    List<ManagerEntity> getAllManagers();
    @Insert("insert into managers(" +
            "first_name, last_name, post_code, prefecture, city, street, other, phone_number, birth_day, gender, good_language" +
            ") " +
            "values(#{firstName}, #{lastName}, #{postCode}, #{prefecture}, #{city}, #{street}, #{other}, #{phoneNumber}, #{birthDay}, #{gender}, #{goodLanguage})")
    void insert(String firstName, String lastName, String postCode, String prefecture, String city, String street, String other, String phoneNumber, Date birthDay, String gender, String goodLanguage);
}

