package com.example.patient.controllers;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.UserEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.exceptions.ErrorChargingData;
import com.example.patient.exceptions.InvalidFiledData;
import com.example.patient.exceptions.SavingElementFailed;
import com.example.patient.exceptions.UnknownErrorCauses;
import com.example.patient.repositories.VilleRepository;
import com.example.patient.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    // todo extraire les methodes depuis VilleController
    // faire des exception sur les villes pour qu'elles ne se repetent pas
    public final VilleRepository villeRepository;


    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @Autowired
    private VilleService villeService;

    @RequestMapping("/all")
    public String getAllVilles(Model model){
        String errorMessage = null;
        model.addAttribute("titre","Liste des villes ");
        model.addAttribute("actionToDoText","Ajouter une nouvelle ville par ici");
        model.addAttribute("actionToDoLink","ville/add");

        try {
            model.addAttribute("villes" , villeService.findAllVilles());

        } catch (Exception e) {
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);

        }
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
    public String addVillePost(HttpServletRequest request, Model model){
        String errorMessage = null;
        String nom =request.getParameter("nom");
        String code = request.getParameter("codePostal") ;
        try {
            villeService.addVille( nom, code);
            return "redirect:/ville/all";
        } catch (Exception e) {
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("ville", new VilleEntity(nom, Integer.parseInt(code)) );
            return "ville/add_edit";
        }
    }

    // partie edit ville
    @GetMapping("/edit/{id}")
    public String editVilleGet(@PathVariable int id, Model model){
        String errorMessage = null;
        model.addAttribute("titre","modifier la ville");
        try{
            model.addAttribute("ville", villeService.findById(id));
        }catch(Exception e){
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        return "ville/add_edit";
    }

    @PostMapping("/edit/{id}")
    public String editVillePost(@PathVariable int id, HttpServletRequest request, Model model){
        String errorMessage = null;
        String nom =request.getParameter("nom");
        String code = request.getParameter("codePostal") ;
        try{
            villeService.editVille(id, nom, code);
            return "redirect:/ville/all";
        }catch(Exception e){
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("ville", new VilleEntity(nom, Integer.parseInt(code)) );
            return editVilleGet(id, model);
        }

    }
    // partie delete ville
    @GetMapping("/delete/{id}")
    public String deleteVille(@PathVariable int id){

        try {
//            villeRepository.deleteById(Integer.parseInt(id));
            villeService.deleteVille(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/ville/all";
    }


}
