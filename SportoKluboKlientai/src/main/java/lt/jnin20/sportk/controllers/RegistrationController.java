package lt.jnin20.sportk.controllers;

import lt.jnin20.sportk.entities.Client;
import lt.jnin20.sportk.entities.Registration;
import lt.jnin20.sportk.entities.Workout;
import lt.jnin20.sportk.repositories.ClientRepository;
import lt.jnin20.sportk.repositories.RegistrationRepository;
import lt.jnin20.sportk.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
@Controller
public class RegistrationController {
    @Autowired
    public RegistrationRepository registrationRepository;

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public WorkoutRepository workoutRepository;

    @GetMapping("/registrations")
    public String registrations(Model mod){
        List<Registration> registrations = registrationRepository.findAll();
        mod.addAttribute("registrations", registrations);
        return "client_registrations";
    }

    @GetMapping("/registration/new")
    public String newRegistration(Model mod){
        List<Client> clients = clientRepository.findAll();
        mod.addAttribute("clients", clients);

        List<Workout> workouts = workoutRepository.findAll();
        mod.addAttribute("workouts", workouts);
        return "registration_new";
    }

    @PostMapping("/registration/new")
    public String storeClient(
            @RequestParam("client_id") Integer client_id,
            @RequestParam("workout_id") Integer workout_id,
            @RequestParam("registration_date") LocalDate registration_date
    ){
        Client c = clientRepository.getReferenceById(client_id);
        Workout w = workoutRepository.getReferenceById(workout_id);
        //LocalDate today = today;
        registration_date = LocalDate.now();
        Registration r = new Registration(c, w, registration_date);
        registrationRepository.save(r);
        return "redirect:/registrations";
    }

    @GetMapping("/delete/registration/{id}")
    public String deleteRegistration(
            @PathVariable("id") Integer id
    ){
        Registration r = registrationRepository.getReferenceById(id);
        clientRepository.deleteById(r.getClient().getId());
        workoutRepository.deleteById(r.getWorkout().getId());

        registrationRepository.deleteById(id);
        return "redirect:/registrations";
    }

}
