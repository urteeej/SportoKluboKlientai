package lt.jnin20.sportk.repositories;

import lt.jnin20.sportk.entities.Client;
import lt.jnin20.sportk.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
}
