package org.service;

import org.dao.TaskUpdateDao;
import org.dao.TaskUpdateDaoImplementation;
import org.models.TaskUpdate;

import java.util.List;

public class TaskUpdateService {
    TaskUpdateDao taskUpdateDao;

    public TaskUpdateService(TaskUpdateDaoImplementation taskUpdateDao){
        this.taskUpdateDao=taskUpdateDao;
    }

    public TaskUpdateService(){
        taskUpdateDao=new TaskUpdateDaoImplementation();
    }

    public TaskUpdate createTaskUpdate(TaskUpdate taskUpdate){
        return taskUpdateDao.addTaskUpdate(taskUpdate);
    }

    public List<TaskUpdate> getTaskUpdateByTaskId(int task_id){
        return taskUpdateDao.taskupdatesOfaTask(task_id);
    }
}
