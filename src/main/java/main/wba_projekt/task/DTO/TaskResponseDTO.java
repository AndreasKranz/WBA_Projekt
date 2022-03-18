package main.wba_projekt.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * dto send with http response
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponseDTO implements Serializable {

    String code;
}
