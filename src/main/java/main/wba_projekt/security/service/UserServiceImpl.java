package main.wba_projekt.security.service;

import main.wba_projekt.security.model.User;
import main.wba_projekt.security.repository.UserRepository;
import main.wba_projekt.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public void addCreatedTask(Task newTask) {
      //Todo
    }

    @Override
    public String[] listEmails() {
        List<User> users = userRepo.findAll();

        String[] emails = new String[users.size()];

        for (int i = 0; i< users.size();i++){
            emails[i] = users.get(i).getEmail();
        }


        return emails;
    }
}
