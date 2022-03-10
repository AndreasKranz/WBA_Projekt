package main.wba_projekt.task.DTO;

import lombok.*;
import main.wba_projekt.task.model.TaskPriority;
import main.wba_projekt.task.model.TaskStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TaskDTO {

    String taskTitle, tdescription, authorEmail, assignedEmail;
    LocalDateTime creationDate, editDate;
    TaskPriority tPriority;
    TaskStatus status;
    Long taskId;


}
