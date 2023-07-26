package com.tctm.practiceapp.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TaskForm {

    @NotEmpty(message = "タイトルを入力してください。")
    private String title;
    @NotEmpty(message = "内容を入力してください。")
    private String content;
    @NotEmpty(message = "期日を入力してください。")
    private String period;
    @NotEmpty(message = "優先度を入力してください。")
    private String importance;
    @NotNull(message = "担当者を入力してください。")
    private Integer manager_id;

}
