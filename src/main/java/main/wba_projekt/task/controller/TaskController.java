package main.wba_projekt.task.controller;

import main.wba_projekt.task.DTO.CommentDTO;
import main.wba_projekt.task.DTO.CommentResponseDTO;
import main.wba_projekt.task.DTO.TaskDTO;
import main.wba_projekt.task.DTO.TaskResponseDTO;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;
import main.wba_projekt.task.repository.CommentRepository;
import main.wba_projekt.task.repository.TaskRepository;
import main.wba_projekt.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    CommentRepository commentRepo;

    @PostMapping(value = "/task/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody TaskDTO input,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        input.setAuthorEmail(principal.getName());


        Task newTask = taskService.createTask(input);


        taskRepo.save(newTask);

        if (true) {
            return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe wurde erfolgreich erstellt"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TaskResponseDTO("Aufgabe konnte nicht erstellt werden"));
        }
    }

    @RequestMapping("/taskform")
    public String retrieveCreateTaskForm(){
        return "createTaskFragment";
    }

    @RequestMapping("/taskDetails")
    public String retrieveTaskDetails(){
        return "taskDetailsFragment";
    }



    @RequestMapping(value = "/tasks", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> listAllTask() {
        List<TaskDTO> tasks = taskService.listAllTasks();
        TaskDTO[] taskArr = tasks.toArray(new TaskDTO[0]);


        return ResponseEntity.status(HttpStatus.OK).body(taskArr);
    }


    @RequestMapping(value = "/task/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editTask(@RequestBody TaskDTO input){

        Task editedTask = taskService.editTask(input);
        log.debug(editedTask.toString());
        taskRepo.save(editedTask);

        if (true) {
            return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe konnte angepasst werden"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TaskResponseDTO("Aufgabenänderung konnte nicht ausgeführt werden"));
        }
    }


    @PostMapping(value = "/task/writecomment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> writeComment(@RequestBody CommentDTO input, HttpServletRequest request){
        String commentAuthor = request.getUserPrincipal().getName();
        input.setAuthorEmail(commentAuthor);
        Comment addedComment = taskService.addComment(input);

        commentRepo.save(addedComment);

        return ResponseEntity.status(HttpStatus.OK).body(new CommentResponseDTO("Neues Kommentar wurde erstellt"));
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public void currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        log.debug("Principal ->" + principal.getName());
    }

    @DeleteMapping("/task/{id}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable String id){
        taskService.deleteTask(Long.valueOf(id));

        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe wurde erfolgreich gelöscht"));
    }

    @RequestMapping(value = "/task/{id}/", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> getTask(@PathVariable String id){
        Long taskId = Long.valueOf(id);

        TaskDTO dto = taskService.getTask(taskId);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @GetMapping(value = "/task/{id}/comments")
    public ResponseEntity<?> test(@PathVariable String id){
        Long taskId = Long.valueOf(id);
        List<CommentDTO> dtos = taskService.listAllComments(taskId);

        CommentDTO[] payload = dtos.toArray(new CommentDTO[0]);

        return ResponseEntity.status(HttpStatus.OK).body(payload);
    }

}
