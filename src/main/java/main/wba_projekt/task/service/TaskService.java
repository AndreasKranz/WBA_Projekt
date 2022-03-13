package main.wba_projekt.task.service;

import main.wba_projekt.task.DTO.CommentDTO;
import main.wba_projekt.task.DTO.TaskDTO;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TaskService {

    Task createTask(TaskDTO input) ;


    Comment addComment(CommentDTO commentInput);

    Task editTask(TaskDTO input);

    List<TaskDTO> listAllTasks();

    TaskDTO getTask(Long id);

    List<CommentDTO> listAllComments(Long taskId);


    void deleteTask(Long taskId);
}
