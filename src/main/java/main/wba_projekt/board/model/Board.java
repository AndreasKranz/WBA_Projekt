package main.wba_projekt.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.security.model.User;
import main.wba_projekt.task.model.Task;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString(exclude = {"users","tasks"})
@NoArgsConstructor
public class Board extends BaseEntity<Long> {

    private String title;


    @ManyToMany(mappedBy = "boards", cascade = CascadeType.PERSIST)
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "board", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private Set<Task> tasks = new LinkedHashSet<>();

}
