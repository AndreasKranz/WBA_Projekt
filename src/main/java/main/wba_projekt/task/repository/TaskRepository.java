package main.wba_projekt.task.repository;

import main.wba_projekt.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t FROM Task t WHERE t.id =?1")
    Task findTaskById(Long id);

}
