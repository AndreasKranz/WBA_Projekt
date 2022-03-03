package main.wba_projekt.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main.wba_projekt.task.model.TaskPriority;
import main.wba_projekt.task.model.TaskStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaskDTO {

    String title, description, authorEmail, assignedEmail;
    LocalDateTime creationDate, editDate;
    TaskPriority priority;
    TaskStatus status;


}
