package main.wba_projekt.task.DTO;

import lombok.*;
import main.wba_projekt.task.model.TaskPriority;
import main.wba_projekt.task.model.TaskStatus;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class TaskEditDTO {
    String taskTitle, tdescription, assignedEmail;
    LocalDateTime editDate;
    TaskPriority tPriority;
    TaskStatus status;
    Long taskId;

    public TaskEditDTO(){
        this.editDate = LocalDateTime.now();
    }
}
