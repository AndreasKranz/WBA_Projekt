package main.wba_projekt.task.repository;

import main.wba_projekt.task.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //TODO findbyID implementieren
}
