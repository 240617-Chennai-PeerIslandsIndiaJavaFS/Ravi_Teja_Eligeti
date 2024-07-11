package org.service;

import org.dao.TaskDao;
import org.dao.TaskDaoImplementation;
import org.models.Task;

import java.util.List;

public class TaskService {
    TaskDao tdao;

    public TaskService(TaskDaoImplementation tdao){
        this.tdao=tdao;
    }

    public TaskService(){
        tdao=new TaskDaoImplementation();
    }

    public Task createTask(Task task){
        System.out.println("🟢✅ Task created successfully ✅🟢");
        return tdao.createTask(task);
    }

    public List<Task>  userProjectTasks(int project_id,int user_id){
        return tdao.userTasks(project_id,user_id);
    }

    public int updateTask(Task task){
            return tdao.updateTask(task);
    }

    public List<Task> getTaskByProject(int project_id){
        return tdao.getAllProjectTasks(project_id);
    }
}
