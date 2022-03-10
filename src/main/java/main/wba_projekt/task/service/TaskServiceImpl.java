package main.wba_projekt.task.service;

import main.wba_projekt.security.repository.UserRepository;
import main.wba_projekt.task.DTO.CommentDTO;
import main.wba_projekt.task.DTO.TaskDTO;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;
import main.wba_projekt.task.model.TaskPriority;
import main.wba_projekt.task.model.TaskStatus;
import main.wba_projekt.task.repository.CommentRepository;
import main.wba_projekt.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Component
public class TaskServiceImpl implements TaskService{

    @Autowired
    UserRepository userRepo;

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    CommentRepository commentRepo;



    public Task createTask(TaskDTO input) {
        Task task = new Task();

        task.setAuthor(userRepo.findByEmail(input.getAuthorEmail()));
        task.setAssignee(userRepo.findByEmail(input.getAssignedEmail()));
        task.setCreateDate(input.getCreationDate());
        task.setEditDate(input.getCreationDate());
        task.setDescription(input.getTdescription());
        task.setStatus(TaskStatus.BACKLOG);
        task.setPriority(TaskPriority.NORMAL);

        return task;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        List<TaskDTO> dtos = new ArrayList<>();
        for (Task t:tasks){
            dtos.add(castTaskToTaskDTO(t));
        }
        return dtos;
    }

    @Override
    public TaskDTO getTask(Long id) {
        Task task = taskRepo.findTaskById(id);
        TaskDTO dto = castTaskToTaskDTO(task);

            return dto;
    }

    private TaskDTO castTaskToTaskDTO(Task task){
        TaskDTO dto = new TaskDTO();

        dto.setTaskTitle(task.getTitle());
        dto.setTdescription(task.getDescription());
        dto.setEditDate(task.getEditDate());
        dto.setCreationDate(task.getCreateDate());
        dto.setTPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setAssignedEmail(task.getAssignee().getEmail());
        dto.setAuthorEmail(task.getAuthor().getEmail());
        dto.setTaskId(task.getId());

        return dto;
    }

    @Override
    public List<CommentDTO> listAllComments(Long taskId) {
        Task task = taskRepo.findTaskById(taskId);

        List<Comment> comments = task.getComments();
        List<CommentDTO> dtos = new ArrayList<>();

        for (Comment c:comments){
            dtos.add(castCommentToCommentDTO(c));
        }
        return dtos;
    }

    private CommentDTO castCommentToCommentDTO(Comment input){
        CommentDTO cDto = new CommentDTO();

        cDto.setTaskId(input.getTask().getId());
        cDto.setText(input.getText());
        cDto.setAuthorEmail(input.getComment_author().getEmail());
        cDto.setId(cDto.getId());
        cDto.setCreateDateTime(input.getCreateDate());

        return cDto;
    }

    @Override
    public void addComment(CommentDTO commentInput) {
        Task task = taskRepo.findTaskById(commentInput.getTaskId());
        Comment newComment = new Comment();

        newComment.setComment_author(userRepo.findByEmail(commentInput.getAuthorEmail()));
        newComment.setTask(task);
        //newComment.setCreateDate(LocalDateTime.now()); für Test
        newComment.setCreateDate(LocalDateTime.of(2000,1,1,1,1));
        newComment.setText(commentInput.getText());

        task.addComments(newComment);

        commentRepo.save(newComment);
        taskRepo.save(task);
    }

    public Task editTask(TaskDTO input){
        Task task = taskRepo.findTaskById(input.getTaskId());

        task.setDescription(input.getTdescription());
        task.setEditDate(LocalDateTime.now());
        task.setAssignee(userRepo.findByEmail(input.getAssignedEmail()));
        task.setPriority(input.getTPriority());
        task.setStatus(input.getStatus());
        task.setPriority(input.getTPriority());

        return task;
    }

    @Override
    public void deleteTask(TaskDTO input){
        Task task = taskRepo.findTaskById(input.getTaskId());
        List<Comment> comments = task.getComments();

        for (Comment c:comments){
            commentRepo.delete(c);
        }

        taskRepo.delete(task);
    }

}
