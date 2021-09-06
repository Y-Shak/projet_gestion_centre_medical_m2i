package com.example.patient.controllers;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.UserEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
@Secured("ROLE_ADMIN")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/all")
    public String getAllPatients(Model model){
        model.addAttribute("titre","Liste des utilisateurs ");
        model.addAttribute("actionToDoText","Ajouter un nouveau utilisateur par ici");
        model.addAttribute("actionToDoLink","user/add");


        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        model.addAttribute("users" , users);
        return "user/list";
    }

    //    partie add user
    @GetMapping("/add")
    public String addUserGet(Model model){
        model.addAttribute("titre","ajouter un utilisateur");
//        UserEntity user = new UserEntity();

        model.addAttribute("user", null);

        return "user/add_edit";
    }

    @PostMapping("/add")
    public String addUserPost(HttpServletRequest request){
        try {
            UserEntity user = new UserEntity();

            user.setEmail((String) request.getParameter("email"));
            user.setUsername((String) request.getParameter("username"));

            String password = (String) request.getParameter("password");
            user.setPassword(passwordEncoder.encode(password));

            user.setRoles((String) request.getParameter("roles"));
            user.setName((String) request.getParameter("name"));
            user.setPhotouser((String) request.getParameter("photouser"));


            userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        // ici redirect vers lien du controller
        return "redirect:/user/all";
    }

    // partie edit user
    @GetMapping("/edit/{id}")
    public String editUserGet(@PathVariable String id, Model model){
        model.addAttribute("titre","modifier l'utilisateur");
        try{
            UserEntity user = userRepository.findById(Integer.parseInt(id)).get();
            model.addAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "user/add_edit";
    }
    @PostMapping("/edit/{id}")
    public String editUserPost(@PathVariable String id, HttpServletRequest request){
        try{
            UserEntity user = userRepository.findById(Integer.parseInt(id)).get();
//            user.setEmail((String) request.getParameter("email"));
//            user.setRoles((String) request.getParameter("roles"));

            String password = (String) request.getParameter("password");
            user.setPassword(passwordEncoder.encode(password));
            user.setUsername((String) request.getParameter("username"));
            user.setName((String) request.getParameter("name"));
            user.setPhotouser((String) request.getParameter("photouser"));

            userRepository.save(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/user/all";
    }

    // partie delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){

        try {
            userRepository.deleteById(Integer.parseInt(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/user/all";
    }
}
