package com.example.patient.controllers;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.UserEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.VilleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ville")
public class VilleController {
    public final VilleRepository villeRepository;

    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }
    @RequestMapping("/all")
    public String getAllVilles(Model model){
        model.addAttribute("titre","Liste des villes ");
        model.addAttribute("actionToDoText","Ajouter une nouvelle ville par ici");
        model.addAttribute("actionToDoLink","ville/add");


        List<VilleEntity> villes = (List<VilleEntity>) villeRepository.findAll();
        model.addAttribute("villes" , villes);
        return "ville/list";
    }

    // partie add ville
    @GetMapping("/add")
    public String addVilleGet(Model model){
        model.addAttribute("titre","ajouter un utilisateur");
        model.addAttribute("ville", null);

        return "ville/add_edit";
    }
    @PostMapping("/add")
    public String addVillePost(HttpServletRequest request){
        VilleEntity ville = new VilleEntity();
        ville.setNom(request.getParameter("nom"));
        ville.setCodePostal(Integer.parseInt(request.getParameter("codePostal")));

        villeRepository.save(ville);
        return "redirect:/ville/all";
    }

    // partie edit ville
    @GetMapping("/edit/{id}")
    public String editVilleGet(@PathVariable String id, Model model){
        model.addAttribute("titre","modifier la ville");
        try{
            VilleEntity ville = villeRepository.findById(Integer.parseInt(id)).get();
            model.addAttribute("ville", ville);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "ville/add_edit";
    }

    @PostMapping("/edit/{id}")
    public String editVillePost(@PathVariable String id, HttpServletRequest request){
        try{
            VilleEntity ville = villeRepository.findById(Integer.parseInt(id)).get();
            ville.setNom(request.getParameter("nom"));
            ville.setCodePostal(Integer.parseInt(request.getParameter("codePostal")));

            villeRepository.save(ville);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/ville/all";
    }
    // partie delete ville
    @GetMapping("/delete/{id}")
    public String deleteVille(@PathVariable String id){

        try {
            villeRepository.deleteById(Integer.parseInt(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/ville/all";
    }


}
