package com.javarush.springproject1.services;


import com.javarush.springproject1.dao.TaskDao;
import com.javarush.springproject1.domain.Status;
import com.javarush.springproject1.domain.Task;
import com.javarush.springproject1.exception.MyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getAll(int offset, int limit) {
        return taskDao.getAllTasks(offset, limit);
    }

    public Long getPagesCount() {
        return taskDao.pagesCount();
    }

    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Transactional
    public void create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDao.create(task);
    }

    @Transactional
    public void update(int id, String description, Status status) {
        Task task = taskDao.getTaskById(id);
        if (task != null) {
            task.setStatus(status);
            task.setDescription(description);
            taskDao.update(task);
        } else {
            throw new MyException("There is no such Task, try to update another one");
        }
    }

    @Transactional
    public void delete(int id) {
        Task task = taskDao.getTaskById(id);
        if (task != null) {
            taskDao.delete(task);
        } else {
            throw new MyException("There is no such Task, try to delete another one");
        }
    }


}
