package lt.jnin20.sportk.repositories;

import lt.jnin20.sportk.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
