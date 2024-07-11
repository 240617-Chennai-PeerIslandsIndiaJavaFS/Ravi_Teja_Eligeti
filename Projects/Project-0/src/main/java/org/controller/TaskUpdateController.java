package org.controller;

import org.models.TaskUpdate;

import org.service.TaskUpdateService;

import java.util.List;

public class TaskUpdateController {
    public TaskUpdate createTimeLine(TaskUpdate taskUpdate){
        return new TaskUpdateService().createTaskUpdate(taskUpdate);
    }
    public List<TaskUpdate> taskUpdatesOfTask(int task_id){
        return new TaskUpdateService().getTaskUpdateByTaskId(task_id);
    }
}
