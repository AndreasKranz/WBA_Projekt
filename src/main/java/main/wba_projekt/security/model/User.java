package main.wba_projekt.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.board.model.Board;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;

import javax.persistence.*;
import java.util.*;

@Entity
@Setter
@Getter
@ToString(exclude = {"boards","createdtasks","assigned_tasks","written_comments"})
@NoArgsConstructor
public class User extends BaseEntity<Long> {

    private String name;

    private Boolean Admin;

    //Relation zu Board, Task und Kommentar machen
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name= "boards_visible",
            joinColumns=@JoinColumn(name= "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id")
    )
    private Set<Board> boards = new java.util.LinkedHashSet<>();


    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Task> createdtasks = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tasks_id"))
    private Set<Task> assigned_tasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "comment_author", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> written_comments = new ArrayList<>();

}
