package com.xkong.todolist.controller;

import com.xkong.todolist.dao.TaskDao;
import com.xkong.todolist.service.TaskService;
import com.xkong.todolist.utils.ResBody;
import com.xkong.todolist.utils.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResBody createTask(@RequestBody TaskDao task) {
        String message = taskService.createTask(task);
        if(Objects.equals(message, "success")) {
            return new ResBody(true, message);
        } else{
            return new ResBody(false, message);
        }
    }

    @GetMapping("/get")
    public List<Task> getTasks(@RequestParam String username) {
        return taskService.getTasks(username);
    }

    @PostMapping("/delete")
    public ResBody deleteTask(@RequestParam String id) {
        String message = taskService.deleteTask(id);
        if(Objects.equals(message, "success")) {
            return new ResBody(true, message);
        } else{
            return new ResBody(false, message);
        }
    }

    @PostMapping("/update")
    public ResBody updateTask(@RequestBody Task task) {
        String message = taskService.updateTask(task);
        if(Objects.equals(message, "success")) {
            return new ResBody(true, message);
        } else{
            return new ResBody(false, message);
        }
    }
}
