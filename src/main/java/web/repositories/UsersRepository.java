package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

}
