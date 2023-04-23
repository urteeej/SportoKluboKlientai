package lt.jnin20.sportk.controllers;

import jakarta.validation.Valid;
import lt.jnin20.sportk.entities.Client;
import lt.jnin20.sportk.entities.Registration;
import lt.jnin20.sportk.repositories.ClientRepository;
import lt.jnin20.sportk.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public RegistrationRepository registrationRepository;


    @GetMapping("/")
    public String clients(Model model){
        List<Client> clients = clientRepository.findAll();
        //for(Client c:clients){
        //    System.out.println("Klientas: " + c.getName() + " " + c.getSurname());
        //    for(Registration r:c.getRegistrations()){
        //        System.out.println("Registracija: " + r.getWorkout().getName() + ", "
        //                + r.getWorkout().getLocation() + ", " + r.getWorkout().getDate());
        //    }
        //}
        model.addAttribute("clients", clients);
        return "client_info_list";
    }

    @GetMapping("/new")
    public String newClient(Model model){
        model.addAttribute("client", new Client());
        return "client_new";
    }

    @PostMapping("/new")
    public String storeClient(
        @Valid
        @ModelAttribute
        Client client,
        BindingResult result
    ){
        if(result.hasErrors()){
            return "client_new";
        }
        clientRepository.save(client);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(
        @PathVariable("id") Integer id,
        Model model
    ){
        model.addAttribute("client", new Client());
        Client c = clientRepository.getReferenceById(id);
        model.addAttribute("client", c);
        return "client_update";
    }

    @PostMapping("/update/{id}")
    public String save(
            @Valid
            @ModelAttribute
            Client client,
            BindingResult result
            //@PathVariable("id") Integer id,
            //@RequestParam("name") String name,
            //@RequestParam("surname") String surname,
            //@RequestParam("email") String email,
            //@RequestParam("phone") String phone
    ){
        //Client c = clientRepository.getReferenceById(id);
        //c.setName(name);
        //c.setSurname(surname);
        //c.setEmail(email);
        //c.setPhone(phone);

        if(result.hasErrors()){
            return "client_update";
        }
        clientRepository.save(client);
        return "redirect:/";

       // clientRepository.save(c);
       // return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id
    ){
        Client c = clientRepository.getReferenceById(id);
        for(Registration r:c.getRegistrations()){
            registrationRepository.deleteById(r.getId());
        }
        clientRepository.deleteById(id);
        return "redirect:/";
    }

}
