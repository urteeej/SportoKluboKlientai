package lt.jnin20.sportk.repositories;

import lt.jnin20.sportk.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
