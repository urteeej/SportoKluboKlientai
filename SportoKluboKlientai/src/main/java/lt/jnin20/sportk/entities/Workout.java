package lt.jnin20.sportk.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 20, message = "Pavadinimas turi būti 3-20 simbolių ilgio")
    @Column(length = 64)
    private String name;

    @NotNull(message = "Įveskite treniruotės datą.")
    @Future(message = "Pasirinkta data turi būti ateityje.")
    @Column
    private LocalDate date;

    @NotNull(message = "Nurodykite vietų skaičių.")
    @Max(value = 300, message = "Vietų skaičius negali viršyti 300.")
    @Column
    private Integer places;

    @Size(min = 1, max = 40, message = "Lokacijos aprašymas turi būti 1-40 simbolių ilgio.")
    @Column
    private String location;

    @OneToMany(mappedBy = "workout")
    private List<Registration> registrations;

    public Workout() {
    }

    public Workout(String name, LocalDate date, Integer places, String location) {
        this.name = name;
        this.date = date;
        this.places = places;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
