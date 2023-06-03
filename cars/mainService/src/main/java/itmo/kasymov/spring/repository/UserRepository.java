package itmo.kasymov.spring.repository;

import itmo.kasymov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByLogin(String login);
    Boolean existsByLogin(String login);
}
