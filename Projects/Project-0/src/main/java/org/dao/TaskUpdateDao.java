package org.dao;

import org.models.TaskUpdate;

import java.util.List;

public interface TaskUpdateDao {

    public TaskUpdate addTaskUpdate(TaskUpdate taskUpdate);

    public List<TaskUpdate> taskupdatesOfaTask(int task_id);


}
