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

/**
 * controller for main functionality
 */
@Controller
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    CommentRepository commentRepo;

    /**
     * mapping to create a new task
     *
     * @param input   JSON object created at the client site with JS, error handling at the client site too
     * @param request used to determine the user creating the task
     * @return HttpResponse with success declaration
     */
    @PostMapping(value = "/task/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody TaskDTO input, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        //Author is null and is set here at serversite
        input.setAuthorEmail(principal.getName());

        Task newTask = taskService.createTask(input);

        taskRepo.save(newTask);
        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe wurde erfolgreich erstellt"));
    }

    /**
     * delivers html fragment with form to create a new task
     *
     * @return htmlfragment
     */
    @RequestMapping("/taskform")
    public String retrieveCreateTaskForm() {
        return "createTaskFragment";
    }

    /**
     * delivers html fragment with form to edit task and list of comments
     * values are set at the client with JS
     *
     * @return htmlfragment
     */
    @RequestMapping("/taskDetails")
    public String retrieveTaskDetails() {
        return "taskDetailsFragment";
    }

    /**
     * gets all tasks to fill the board
     *
     * @return json array with tasks as DTOs
     */
    @RequestMapping(value = "/tasks", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> listAllTask() {
        List<TaskDTO> tasks = taskService.listAllTasks();
        TaskDTO[] taskArr = tasks.toArray(new TaskDTO[0]);

        return ResponseEntity.status(HttpStatus.OK).body(taskArr);
    }

    /**
     * edit a existing task
     *
     * @param input json object created at the client with JS, error handling for wrong input
     *              also at the client
     * @return http Response with success message
     */
    @RequestMapping(value = "/task/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editTask(@RequestBody TaskDTO input) {
        Task editedTask = taskService.editTask(input);
        taskRepo.save(editedTask);

        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe konnte angepasst werden"));
    }

    /**
     * write a new comment under a certain task
     *
     * @param input   json object created at the client site
     * @param request used to determine author of comment
     * @return http response with sucess message
     */
    @PostMapping(value = "/task/writecomment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> writeComment(@RequestBody CommentDTO input, HttpServletRequest request) {
        String commentAuthor = request.getUserPrincipal().getName();
        input.setAuthorEmail(commentAuthor);
        Comment addedComment = taskService.addComment(input);

        commentRepo.save(addedComment);

        return ResponseEntity.status(HttpStatus.OK).body(new CommentResponseDTO("Neues Kommentar wurde erstellt"));
    }

    /**
     * delete a existing task
     *
     * @param id in url since no body is send
     * @return http response with sucess message
     */
    @DeleteMapping("/task/{id}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        taskService.deleteTask(Long.valueOf(id));

        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO("Aufgabe wurde erfolgreich gelöscht"));
    }

    /**
     * gets a certain tasks details
     *
     * @param id in url used to determine the task asked for
     * @return json object with the details of the task
     */
    @RequestMapping(value = "/task/{id}/", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getTask(@PathVariable String id) {
        Long taskId = Long.valueOf(id);

        TaskDTO dto = taskService.getTask(taskId);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    /**
     * list all Comments related to certain task
     *
     * @param id in url to determine the task which comments are asked for
     * @return json array of objects from the delivered DTOs in the http response
     */
    @GetMapping(value = "/task/{id}/comments")
    public ResponseEntity<?> listAllComments(@PathVariable String id) {
        Long taskId = Long.valueOf(id);
        List<CommentDTO> dtos = taskService.listAllComments(taskId);

        CommentDTO[] payload = dtos.toArray(new CommentDTO[0]);

        return ResponseEntity.status(HttpStatus.OK).body(payload);
    }
}
