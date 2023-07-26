package com.tctm.practiceapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskEntity {
    private long id;
    private String title;
    private String content;
    private String period;
    private String importance;
    private String manager_id;
}