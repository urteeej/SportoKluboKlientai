package lt.jnin20.sportk.controllers;

import jakarta.validation.Valid;
import lt.jnin20.sportk.entities.Client;
import lt.jnin20.sportk.entities.Registration;
import lt.jnin20.sportk.entities.Workout;
import lt.jnin20.sportk.repositories.RegistrationRepository;
import lt.jnin20.sportk.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class WorkoutController {

    @Autowired
    public WorkoutRepository workoutRepository;

    @Autowired
    public RegistrationRepository registrationRepository;

    @GetMapping("/workouts")
    public String workouts(Model m){
        List<Workout> workouts = workoutRepository.findAll();
        //for(Workout w:workouts){
        //    System.out.println("Treniruotė: " + w.getName() + ", " + w.getDate() + ", " + w.getLocation());
        //    for(Registration r:w.getRegistrations()){
        //        System.out.println("Registracija: " + r.getClient().getName() + ", " + r.getClient().getSurname());
        //    }
        //}
        m.addAttribute("workouts", workouts);
        return "workout_info_list";
    }

    @GetMapping("/new/workout")
    public String newWorkout(Model m){
        m.addAttribute("workout", new Workout());
        return "workout_new";
    }

    @PostMapping("/new/workout")
    public String storeWorkout(
            @Valid
            @ModelAttribute
            Workout workout,
            BindingResult result
            //@RequestParam("name") String name,
            //@RequestParam("date") LocalDate date,
            //@RequestParam("places") Integer places,
            //@RequestParam("location") String location
    ){
        //Workout w = new Workout(name, date, places, location);
        if(result.hasErrors()){
            return "workout_new";
        }
        workoutRepository.save(workout);
        return "redirect:/workouts";
    }

    @GetMapping("/update/workout/{id}")
    public String updateWorkout(
            @PathVariable("id") Integer id,
            Model m
    ){
        Workout w = workoutRepository.getReferenceById(id);
        m.addAttribute("workout", w);
        return "workout_update";
    }

    @PostMapping("/update/workout/{id}")
    public String saveWorkout(
            @Valid
            @ModelAttribute
            Workout workout,
            BindingResult result
            //@PathVariable("id") Integer id,
            //@RequestParam("name") String name,
            //@RequestParam("date") LocalDate date,
            //@RequestParam("places") Integer places,
            //@RequestParam("location") String location
    ){
        //Workout w = workoutRepository.getReferenceById(id);
        //w.setName(name);
        //w.setDate(date);
        //w.setPlaces(places);
        //w.setLocation(location);

        if(result.hasErrors()){
            return "workout_update";
        }
        workoutRepository.save(workout);
        return "redirect:/workouts";

        //workoutRepository.save(w);
        //return "redirect:/workouts";
    }

    @GetMapping("/delete/workout/{id}")
    public String deleteWorkout(
            @PathVariable("id") Integer id
    ){
        workoutRepository.deleteById(id);
        return "redirect:/workouts";
    }
}