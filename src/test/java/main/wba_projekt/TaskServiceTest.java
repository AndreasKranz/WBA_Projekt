package main.wba_projekt;

import main.wba_projekt.security.repository.UserRepository;
import main.wba_projekt.task.DTO.CommentDTO;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;
import main.wba_projekt.task.repository.CommentRepository;
import main.wba_projekt.task.repository.TaskRepository;
import main.wba_projekt.task.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TaskServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository repo;

    @Autowired
    TaskServiceImpl service;

    @Test
    public void testAddComment(){

        //TaskServiceImpl service = new TaskServiceImpl();

        Task task = new Task();
        Long id = task.getId();
        task.setAuthor(repo.findByEmail("hans@fritz.com"));

        CommentDTO dto = new CommentDTO();
        dto.setAuthorEmail("hans@fritz.com");
        dto.setText("Hallo ich bin ein Kommentar");
        dto.setTaskId(id);

        service.addComment(dto);

        Comment comment = new Comment();
        comment.setTask(task);
        comment.setComment_author(task.getAuthor());
        comment.setText("Hallo ich bin ein Kommentar");
        comment.setCreateDate(LocalDateTime.of(2000,1,1,1,1));



        Object[] arr = taskRepo.findTaskById(id).getComments().toArray();
        Comment comp = (Comment) arr[0];

        assertThat(comp).isEqualTo(comment);

    }

}
