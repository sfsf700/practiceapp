package com.tctm.practiceapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ManagerEntity {

    private long id;
    private String first_name;
    private String last_name;
    private String post_code;
    private String prefecture;
    private String city;
    private String street;
    private String other;
    private String phone_number;
    private Date birth_day;
    private String gender;
    private String good_language;
}
