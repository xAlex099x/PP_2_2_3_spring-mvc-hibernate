package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import web.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> allUsers() {
        return usersRepository.findAll();
    }

    public User userById(long id) {
        Optional<User> result = usersRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public void addUser(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void updateUser(long id, User updatedUser) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }
}
