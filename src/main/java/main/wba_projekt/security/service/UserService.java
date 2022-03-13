package main.wba_projekt.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface UserService {

    public String[] listEmails();
}
