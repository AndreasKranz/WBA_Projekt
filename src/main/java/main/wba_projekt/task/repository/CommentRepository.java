package main.wba_projekt.task.repository;

import main.wba_projekt.task.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * gets a comment object from db with its
     * @param id
     * @return object of comment
     */
    @Query("select c FROM Comment c WHERE c.id =?1")
    Comment findCommentById(Long id);

}
