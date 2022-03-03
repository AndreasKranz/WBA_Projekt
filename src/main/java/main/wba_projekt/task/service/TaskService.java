package main.wba_projekt.task.service;

import main.wba_projekt.security.repository.UserRepository;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;
import main.wba_projekt.task.DTO.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    UserRepository userRepo;

    public Task createTask(TaskDTO input){

        Task task = new Task();
        task.setAuthor(userRepo.findByEmail(input.getAuthorEmail()));
        task.setUser(userRepo.findByEmail(input.getAssignedEmail()));
        task.setCreateDate(input.getCreationDate());
        task.setEditDate(input.getCreationDate());
        task.setDescription(input.getDescription());


        return task;
    }

    public void addComment(Task task, Comment comment){
        //TODO Fehler- oder Rückmeldung
        comment.setTask(task);
        task.getComments().add(comment);

    }

    public void editTask(){

    }

}
