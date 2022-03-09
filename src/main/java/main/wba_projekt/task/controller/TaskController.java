package main.wba_projekt.task.controller;

import main.wba_projekt.task.DTO.CommentDTO;
import main.wba_projekt.task.DTO.CommentResponseDTO;
import main.wba_projekt.task.DTO.TaskDTO;
import main.wba_projekt.task.DTO.TaskResponseDTO;
import main.wba_projekt.task.model.Task;
import main.wba_projekt.task.repository.CommentRepository;
import main.wba_projekt.task.repository.TaskRepository;
import main.wba_projekt.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    CommentRepository commentRepo;

    @PostMapping(value = "/task/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody TaskDTO input) {

        Task newTask = taskService.createTask(input);

        taskRepo.save(newTask);

        if (true) {
            return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe wurde erfolgreich erstellt"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TaskResponseDTO("Aufgabe konnte nicht erstellt werden"));
        }
    }

    //Unfertig
    @RequestMapping(value = "/tasks", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> listAllTask(Model model) {
        List<TaskDTO> tasks = taskService.listAllTasks();
        TaskDTO[] taskArr = tasks.toArray(new TaskDTO[0]);


        return ResponseEntity.status(HttpStatus.OK).body(taskArr);
    }

    @PostMapping(value = "task/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editTask(@RequestBody TaskDTO input){
        Task editedTask = taskService.editTask(input);

        taskRepo.save(editedTask);

        if (true) {
            return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe konnte angepasst werden"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TaskResponseDTO("Aufgabenänderung konnte nicht ausgeführt werden"));
        }
    }

    @PostMapping(value = "task/writecomment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> writeComment(@RequestBody CommentDTO input){
        taskService.addComment(input);


        return ResponseEntity.status(HttpStatus.OK).body(new CommentResponseDTO("Neues Kommentar wurde erstellt"));
    }

    @DeleteMapping("task/delete")
    public ResponseEntity<?> deleteTask(@RequestBody TaskDTO input){
        taskService.deleteTask(input);

        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe wurde erfolgreich gelöscht"));

    }

}
