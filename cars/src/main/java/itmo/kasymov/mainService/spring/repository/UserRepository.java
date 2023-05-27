package itmo.kasymov.mainService.spring.repository;

import itmo.kasymov.mainService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByLogin(String login);
    Boolean existsByLogin(String login);
}
