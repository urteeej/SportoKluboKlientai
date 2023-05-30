package lt.jnin20.sportk.repositories;

import lt.jnin20.sportk.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
