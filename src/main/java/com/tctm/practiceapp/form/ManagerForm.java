package com.tctm.practiceapp.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ManagerForm {
    @NotNull(message = "名前を入力してください。")
    private String first_name;
    private String last_name;
    private String post_code;
    private String prefecture;
    private String city;
    private String street;
    private String other;
    private String phone_number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth_day;
    private String gender;
    private String good_language;
}
