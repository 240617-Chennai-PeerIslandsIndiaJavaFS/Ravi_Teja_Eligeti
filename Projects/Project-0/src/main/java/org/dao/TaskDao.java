package org.dao;

import org.models.Task;

import java.util.List;

public interface TaskDao {
    public Task createTask(Task task);

    public List<Task> getAllProjectTasks(int project_id);

    public Task getTaskById(int task_id);

    public List<Task> userTasks(int project_id,int user_id);

    public int updateTask(Task task);


}
