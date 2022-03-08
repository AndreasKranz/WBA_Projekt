package main.wba_projekt.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.board.model.Board;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.security.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"assignee","comments"})
@NoArgsConstructor
public class Task extends BaseEntity<Long> {

    private String title;

    private TaskStatus status;

    private TaskPriority priority;

    private String description;

    private LocalDateTime createDate;

    private LocalDateTime editDate;



    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "assigned_user", nullable = false)
    private User assignee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private List<Comment> comments = new java.util.ArrayList<>();

    public void addComments(Comment comment){
        this.comments.add(comment);
    }


}
