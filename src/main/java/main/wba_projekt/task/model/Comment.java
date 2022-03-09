package main.wba_projekt.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.security.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Comment extends BaseEntity<Long> {

    private String text;

    private LocalDateTime createDate;



    @ManyToOne
    @JoinColumn(name = "comment_author_id")
    private User comment_author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}
