package main.wba_projekt.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * used to transport comment information to client
 * and json objects from client are turned into dtos before comment model is created with dto
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentDTO {
    String authorEmail, text;
    LocalDateTime createDateTime;
    Long taskId, id;
}
