package main.wba_projekt.security.repository;

import main.wba_projekt.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
