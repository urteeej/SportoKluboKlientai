package lt.jnin20.sportk.controllers;

import lt.jnin20.sportk.entities.Client;
import lt.jnin20.sportk.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;


    @GetMapping("/")
    public String clients(Model model){
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client_info_list";
    }

    @GetMapping("/new")
    public String newClient(){
        return "client_new";
    }

    @PostMapping("/new")
    public String storeClient(
        @RequestParam("name") String name,
        @RequestParam("surname") String surname,
        @RequestParam("email") String email,
        @RequestParam("phone") String phone
    ){
        Client c = new Client(name, surname, email, phone);
        clientRepository.save(c);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(
        @PathVariable("id") Integer id,
        Model model
    ){
        Client c = clientRepository.getReferenceById(id);
        model.addAttribute("client", c);
        return "client_update";
    }

    @PostMapping("/update/{id}")
    public String save(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ){
        Client c = clientRepository.getReferenceById(id);
        c.setName(name);
        c.setSurname(surname);
        c.setEmail(email);
        c.setPhone(phone);
        clientRepository.save(c);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id
    ){
        clientRepository.deleteById(id);
        return "redirect:/";
    }

}
