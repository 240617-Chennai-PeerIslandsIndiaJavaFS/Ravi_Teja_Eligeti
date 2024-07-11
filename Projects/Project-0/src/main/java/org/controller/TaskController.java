package org.controller;

import org.models.Task;
import org.service.TaskService;

import java.util.List;

public class TaskController {
    public Task createTask(Task task){
        return new TaskService().createTask(task);
    }

    public List<Task> userTasks(int project_id,int user_id){
        return new TaskService().userProjectTasks(project_id,user_id);
    }

    public int updateTask(Task task){
        return new TaskService().updateTask(task);
    }
    public List<Task> getProjectTasks(int project_id){
        return  new TaskService().getTaskByProject(project_id);
    }

}
