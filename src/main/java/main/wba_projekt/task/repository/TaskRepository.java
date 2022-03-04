package main.wba_projekt.task.repository;

import main.wba_projekt.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query()
    Optional<Task> findById(Long id);
    //TODO implementieren
}
