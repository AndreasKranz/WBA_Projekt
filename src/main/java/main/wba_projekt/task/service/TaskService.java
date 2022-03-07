package main.wba_projekt.task.service;

import main.wba_projekt.task.DTO.CommentDTO;
import main.wba_projekt.task.DTO.TaskDTO;
import main.wba_projekt.task.model.Task;

import java.util.ArrayList;

public interface TaskService {

    Task createTask(TaskDTO input) ;

    void addComment(CommentDTO commentInput);

    Task editTask(TaskDTO input);

    ArrayList<TaskDTO> listAllTasks();

    void deleteTask(TaskDTO input);
}
