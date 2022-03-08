package main.wba_projekt.security.service;

import main.wba_projekt.task.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface UserService {

    public void addCreatedTask(Task newTask);
}
