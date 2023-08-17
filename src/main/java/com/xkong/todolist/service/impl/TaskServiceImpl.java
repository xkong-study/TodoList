package com.xkong.todolist.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.xkong.todolist.dao.TaskDao;
import com.xkong.todolist.service.TaskService;
import com.xkong.todolist.utils.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String createTask(TaskDao task) {
        try{
            mongoTemplate.insert(task, "Task");
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteTask(String id) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));
            DeleteResult result = mongoTemplate.remove(query, TaskDao.class);
            long deletedCount = result.getDeletedCount();
            if (deletedCount > 0) {
                return "success";
            } else {
                return "fail: Task not found";
            }
        } catch (Exception e) {
            return "fail: " + e.getMessage();
        }
    }


    @Override
    public String updateTask(Task task) {
        try {
            TaskDao taskDao = mongoTemplate.findOne(Query.query(Criteria.where("id").is(task.getId())), TaskDao.class);
            if(taskDao == null) {
                return "fail: Task not found";
            } else {
                taskDao.setStartDate(task.getStartDate());
                taskDao.setEndDate(task.getEndDate());
                taskDao.setStatus(task.getStatus());
                taskDao.setContent(task.getContent());
                mongoTemplate.save(taskDao);
                return "success";
            }
        } catch (Exception e) {
            return "fail: " + e.getMessage();
        }
    }

    @Override
    public List<Task> getTasks(String username) {
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("username").is(username));
            List<TaskDao> tasks = mongoTemplate.find(query, TaskDao.class);
            return tasks.stream().map(this::convertToTask).toList();
        } catch (Exception e) {
            Task task = new Task();
            task.setContent(e.getMessage());
            return List.of(task);
        }
    }

    private Task convertToTask(TaskDao taskDao) {
        Task task = new Task();
        task.setId(taskDao.getId());
        task.setContent(taskDao.getContent());
        task.setStartDate(taskDao.getStartDate());
        task.setEndDate(taskDao.getEndDate());
        task.setStatus(taskDao.getStatus());
        return task;
    }
}
