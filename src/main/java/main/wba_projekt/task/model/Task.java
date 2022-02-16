package main.wba_projekt.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.board.model.Board;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.security.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"users_assigned","comments"})
@NoArgsConstructor
public class Task extends BaseEntity<Long> {

    private String title;

    private TaskStatus status;

    private TaskPriority priority;

    private String description;

    private LocalDateTime createDate;

    private LocalDateTime editDate;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "board_id", nullable = false, unique = true)
    private Board board;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "assigned_user", nullable = false)
    private User user;

//Anhang als Objekt, wenn es nicht klappt nur Links anbieten TUTORIAL ANGUCKEN



}
