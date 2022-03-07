package main.wba_projekt.task.repository;

import main.wba_projekt.task.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c FROM Comment c WHERE c.id =?1")
    Comment findCommentById(Long id);

}
