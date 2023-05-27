package itmo.kasymov.mainService.spring.controller;

import itmo.kasymov.mainService.entity.User;
import itmo.kasymov.mainService.spring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Пользователь с таким именем уже существует");
        }
        userService.createUser(user);
        return ResponseEntity.ok("Пользователь успешно создан");
    }

}
