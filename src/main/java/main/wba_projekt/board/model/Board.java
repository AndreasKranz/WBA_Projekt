package main.wba_projekt.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.wba_projekt.common.BaseEntity;
import main.wba_projekt.security.model.User;
import main.wba_projekt.task.model.Task;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Entity of the board where the tasks are created and hold
 */
@Entity
@Setter
@Getter
@ToString(exclude = {"users", "tasks"})
@NoArgsConstructor
public class Board extends BaseEntity<Long> {

    private String title;

    @OneToMany(mappedBy = "assigned_board", orphanRemoval = true)
    private List<User> users;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Task> tasks;

}
