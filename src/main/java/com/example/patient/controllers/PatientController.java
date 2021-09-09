package com.example.patient.controllers;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.exceptions.ErrorChargingData;
import com.example.patient.exceptions.ErrorChargingOneElementData;
import com.example.patient.services.PatientService;
import com.example.patient.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private VilleService villeService;
    // partie get all
    @RequestMapping("/all")
    public String getAllPatients(Model model){
        String errorMessage = null;

        model.addAttribute("titre","Liste des patients ");
        model.addAttribute("actionToDoText","Ajouter un nouveau patient par ici ");
        model.addAttribute("actionToDoLink","patient/add");

        try {
            model.addAttribute("patients" , patientService.findAllPatients());
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "patient/list";
    }

    //    partie add patient
    @GetMapping("/add")
    public String addPatientGet(Model model){
        model.addAttribute("titre","ajouter un patient");
        String errorMessage = null;
        model.addAttribute("patient", null);
        try {
            model.addAttribute("villes" , villeService.findAllVilles());
            return "patient/add_edit";
        } catch (Exception e) {
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "patient/add_edit";
        }
    }
    @GetMapping("/edit/{id}")
    public String editPatientGet(@PathVariable int id, Model model){
        model.addAttribute("titre","modifier le patient");
        String errorMessage = null;
        try{
            model.addAttribute("villes" , villeService.findAllVilles());
            model.addAttribute("patient", patientService.findPatientById(id));
        }catch(Exception e){
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }
        return "patient/add_edit";
    }

    @PostMapping("/add")
    public String addPatientPost(HttpServletRequest request,Model model){
        String errorMessage = null;
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String telephone = (String) request.getParameter("telephone");
        String email = (String) request.getParameter("email");
        String photo = (String) request.getParameter("photo");
        try {
            model.addAttribute("villes" , villeService.findAllVilles());
        } catch (Exception e) {
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "patient/add_edit";
        }
        try {
            patientService.addPatient(
                    nom, prenom, telephone, email, photo, (String)  request.getParameter("ville_depuis_template")
            );
            return "redirect:/patient/all";
        }catch (Exception e){
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("patient", new PatientEntity(nom, prenom, telephone, email, photo));
            return "patient/add_edit";
        }
    }

//    partie edit patient

    @PostMapping("/edit/{id}")
    public String editPatientPost(@PathVariable int id, HttpServletRequest request, Model model){
        String errorMessage = null;
        try{
            patientService.editPatient(
                    id,
                    (String) request.getParameter("nom"),
                    (String) request.getParameter("prenom"),
                    (String) request.getParameter("telephone"),
                    (String) request.getParameter("email"),
                    (String) request.getParameter("photo"),
                    (String)  request.getParameter("ville_depuis_template")
            );
            return "redirect:/patient/all";
        }catch(Exception e){
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return editPatientGet(id,model);
        }

    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable int id){

        try {
            patientService.deletePatient(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/patient/all";
    }



}
