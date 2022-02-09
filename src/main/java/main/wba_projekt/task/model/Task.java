package main.wba_projekt.task.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.common.BaseEntity;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Task extends BaseEntity<Long> {

    private String title;

    private TaskStatus status;

    private TaskPriority priority;

    private String description;

    private LocalDateTime createDate;

    private LocalDateTime editDate;

    //Anhang als Objekt, wenn es nicht klappt nur Links anbieten TUTORIAL ANGUCKEN



}
