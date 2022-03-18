package main.wba_projekt.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * response if comment was created send in http response
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentResponseDTO implements Serializable {
    String text;
}
