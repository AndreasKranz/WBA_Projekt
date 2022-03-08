package main.wba_projekt;

import main.wba_projekt.board.model.Board;
import main.wba_projekt.board.repository.BoardRepository;
import main.wba_projekt.security.model.User;
import main.wba_projekt.security.repository.UserRepository;
import main.wba_projekt.task.DTO.CommentDTO;
import main.wba_projekt.task.model.Comment;
import main.wba_projekt.task.model.Task;
import main.wba_projekt.task.repository.CommentRepository;
import main.wba_projekt.task.repository.TaskRepository;
import main.wba_projekt.task.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TaskServiceTest {

   // @Autowired
    //private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    //@MockBean
    @Autowired
    private TaskService service;


    @Autowired
    private BoardRepository boardRepo;

    @Transactional
    @Test
    public void testAddComment(){

        Board board = new Board();
        board.setTitle("gaa");

        boardRepo.save(board);


        Task task = new Task();
        task.setTitle("Beispiel");
        User temp = userRepo.findByEmail("hans@fritz.com");
        task.setAssignee(temp);
        task.setAuthor(userRepo.findByEmail("hans@fritz.com"));
        task.setBoard(board);

        userRepo.save(temp);
        taskRepo.save(task);
        Long id = task.getId();

        CommentDTO dto = new CommentDTO();
        dto.setAuthorEmail("hans@fritz.com");
        dto.setText("Hallo ich bin ein Kommentar");
        dto.setTaskId(id);

        service.addComment(dto);

        taskRepo.save(task);

        Comment comment = new Comment();
        comment.setTask(task);
        comment.setComment_author(task.getAuthor());
        comment.setText("Hallo ich bin ein Kommentar");
        comment.setCreateDate(LocalDateTime.of(2000,1,1,1,1));




        List<Comment> list =  taskRepo.findTaskById(id).getComments();
        Comment comp = list.get(0);

        assertThat(comp).isEqualTo(comment);

    }

}
