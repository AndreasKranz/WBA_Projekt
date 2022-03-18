package main.wba_projekt.security.repository;

import main.wba_projekt.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * gets user entity from db with the apropiate email adress as userprincipalname
     *
     * @param email
     * @return user entity
     */
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
