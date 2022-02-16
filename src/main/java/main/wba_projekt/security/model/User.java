package main.wba_projekt.security.model;

import lombok.*;
import main.wba_projekt.board.model.Board;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import javax.persistence.*;
import java.util.*;

@Entity
@Setter
@Getter
@ToString(exclude = {"boards","createdtasks","assigned_tasks","written_comments"})
@NoArgsConstructor
public class User extends BaseEntity<Long> {


    private String firstname;

    private String lastname;

    private Boolean Admin;

    private String email;

    private String password;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "author",  orphanRemoval = true)
    private List<Task> createdtasks = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "comment_author",  orphanRemoval = true)
    private List<Comment> written_comments = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "assigned_board_id")
    private Board assigned_board;


}
