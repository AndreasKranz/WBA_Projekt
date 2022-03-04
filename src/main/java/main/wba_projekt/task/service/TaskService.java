package main.wba_projekt.task.service;

import main.wba_projekt.task.DTO.TaskDTO;
import main.wba_projekt.task.model.Task;

public interface TaskService {

    Task createTask(TaskDTO input);

    void addComment();

    Task editTask(TaskDTO input);


}
