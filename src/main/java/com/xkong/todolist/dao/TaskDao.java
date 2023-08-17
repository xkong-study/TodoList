package com.xkong.todolist.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Task")
public class TaskDao {
    @Id
    private String id;
    private String username;
    private String content;
    private Date startDate;
    private Date endDate;
    private String status;
}
