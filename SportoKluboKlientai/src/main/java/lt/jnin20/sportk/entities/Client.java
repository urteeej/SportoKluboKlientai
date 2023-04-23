package lt.jnin20.sportk.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;


@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 20, message = "Vardas turi būti 3-20 simbolių ilgio.")
    @Column(length = 64)
    private String name;

    @Size(min = 3, max = 25, message = "Pavardė turi būti 3-25 simbolių ilgio.")
    @Column(length = 64)
    private String surname;

    @NotEmpty(message = "Įveskite el. pašto adresą.")
    @Email(message = "El. pašto adresas turi turėti '@' ženklą, jo dešinėje nurodant pašto serverio adresą (pvz. ku.lt), o kairėje - abonento vardą.")
    @Column(length = 128)
    private String email;

    @Size(max = 15, message = "Telefono numeris turi būti ne ilgesnis nei 15 simbolių.")
    @Column(length = 64)
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Registration> registrations;

    public Client() {
    }

    public Client(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
