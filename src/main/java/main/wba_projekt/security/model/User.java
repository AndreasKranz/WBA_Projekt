package main.wba_projekt.security.model;

import lombok.*;
import main.wba_projekt.board.model.Board;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;

import javax.persistence.*;
import java.util.*;

/**
 * model of user storred in db
 */
@Entity
@Setter
@Getter
@ToString(exclude = {"createdtasks", "assigned_board", "written_comments"})
@NoArgsConstructor
public class User extends BaseEntity<Long> {

    private String firstName;

    private String lastName;

    private Boolean Admin;

    private String email;

    private String password;


    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Task> createdtasks;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "comment_author", orphanRemoval = true)
    private List<Comment> written_comments;

    @ManyToOne()
    @JoinColumn(name = "assigned_board_id")
    private Board assigned_board;


}
