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

    String taskTitle, description, authorEmail, assignedEmail;
    LocalDateTime creationDate, editDate;
    TaskPriority priority;
    TaskStatus status;
    Long taskId;


}
