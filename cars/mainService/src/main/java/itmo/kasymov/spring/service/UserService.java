package itmo.kasymov.spring.service;

import itmo.kasymov.entity.User;
import itmo.kasymov.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) throws Exception {
        if (userRepository == null)
            throw new Exception("Repository is null.");
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
    }
    public User getUserByUsername(String username) {
        return userRepository.getByLogin(username);
    }
    public Boolean existsByUsername(String username) {
        return userRepository.existsByLogin(username);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User updateBrand(Long id, User newUser) throws Exception {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        existingUser.setUsername(newUser.getUsername());
        existingUser.setPassword(newUser.getPassword());
        existingUser.setRole(newUser.getRole());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) throws Exception {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        userRepository.delete(existingUser);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found.");
        return user;
    }
}
