package com.xkong.todolist.service;

import com.xkong.todolist.dao.TaskDao;
import com.xkong.todolist.utils.Task;

import java.util.List;

public interface TaskService {
    String createTask(TaskDao task);

    String deleteTask(String id);

    String updateTask(Task task);

    List<Task> getTasks(String username);
}
