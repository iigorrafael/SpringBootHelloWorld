package br.com.springboot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private List<User> users = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {
        
        // System.out.println("O id é "+id);
    
        // Optional<User> userFind = users.stream().filter(user -> user.getId() == id).findFirst();
        Optional<User> userFind = this.userRepository.findById(id);

        if (userFind.isPresent()) {
            return userFind.get();
        }

        return null;

        // User user = new User();
        // user.setId(1L);
        // user.setName("Igor");
        // user.setUsername("igorrafael");
        // return user;
    }


    @PostMapping("/")
    public User user(@RequestBody User user) {
        // users.add(user);
        return this.userRepository.save(user);
        // return user;
    }

    @GetMapping("/List")
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @GetMapping("/List/{id}")
    public List<User> listMoreThan(@PathVariable("id") Long id) {
        // return this.userRepository.findAllMoreThan(id);
       return this.userRepository.findByIdGreaterThan(id);
    }

    @GetMapping("/findByName/{name}")
    public List<User> findByName(@PathVariable("name") String name) {
        // return this.userRepository.findAllMoreThan(id);
       return this.userRepository.findByNameIgnoreCase(name);
    }    

}
