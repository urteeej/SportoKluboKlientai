package lt.jnin20.sportk.controllers;

import lt.jnin20.sportk.entities.Registration;
import lt.jnin20.sportk.entities.Workout;
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
    public String newWorkout(){
        return "workout_new";
    }

    @PostMapping("/new/workout")
    public String storeWorkout(
            @RequestParam("name") String name,
            @RequestParam("date") LocalDate date,
            @RequestParam("places") Integer places,
            @RequestParam("location") String location
    ){
        Workout w = new Workout(name, date, places, location);
        workoutRepository.save(w);
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
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("date") LocalDate date,
            @RequestParam("places") Integer places,
            @RequestParam("location") String location
    ){
        Workout w = workoutRepository.getReferenceById(id);
        w.setName(name);
        w.setDate(date);
        w.setPlaces(places);
        w.setLocation(location);
        workoutRepository.save(w);
        return "redirect:/workouts";
    }

    @GetMapping("/delete/workout/{id}")
    public String deleteWorkout(
            @PathVariable("id") Integer id
    ){
        workoutRepository.deleteById(id);
        return "redirect:/workouts";
    }
}