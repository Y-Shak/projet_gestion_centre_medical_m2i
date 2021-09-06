package com.example.patient.controllers;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.repositories.PatientRepository;
import com.example.patient.repositories.VilleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;
    private final VilleRepository villeRepository;

    public PatientController(PatientRepository patientRepository, VilleRepository villeRepository) {
        this.patientRepository = patientRepository;
        this.villeRepository = villeRepository;
    }

    //    partie add patient
    @GetMapping("/add")
    public String addPatientGet(Model model){
        model.addAttribute("titre","ajouter un patient");
        List<VilleEntity> villes = (List<VilleEntity>) villeRepository.findAll();
        model.addAttribute("villes" , villes);
        PatientEntity patient = new PatientEntity();
//        patient.setId(0);
//        patient.setNom("");
//        patient.setPrenom("");
        VilleEntity v = new VilleEntity();
//        v.setId(0);
        patient.setVille(v);
        model.addAttribute("patient", patient);
//        model.addAttribute("patient", new PatientEntity());
        return "patient/add_edit";
    }

    @PostMapping("/add")
    public String addPatientPost(HttpServletRequest request,Model model){
        String errorMessage = null;
        try {
            PatientEntity p = new PatientEntity();
            p.setNom((String) request.getParameter("nom"));
            p.setPrenom((String) request.getParameter("prenom"));
            p.setTelephone((String) request.getParameter("telephone"));
            p.setEmail((String) request.getParameter("email"));
            p.setPhoto((String) request.getParameter("photo"));

            VilleEntity ville = new VilleEntity();
            ville.setId(Integer.parseInt(request.getParameter("ville_depuis_template")));
            p.setVille(ville);
            p= null;
            patientRepository.save(p);
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/patient/all";
        }catch (Exception e){
            e.printStackTrace();
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "patient/add_edit";
        }
    }

//    partie edit patient
    @GetMapping("/edit/{id}")
    public String editPatientGet(@PathVariable String id, Model model){
        model.addAttribute("titre","modifier le patient");
        try{
            List<VilleEntity> villes = (List<VilleEntity>) villeRepository.findAll();
            model.addAttribute("villes" , villes);
            PatientEntity patient = patientRepository.findById(Integer.parseInt(id)).get();
            model.addAttribute("patient", patient);
        }catch(Exception e){
            e.printStackTrace();

        }

        return "patient/add_edit";
    }
    @PostMapping("/edit/{id}")
    public String editPatientPost(@PathVariable String id, HttpServletRequest request, Model model){
        String errorMessage = null;
        try{
            PatientEntity p = patientRepository.findById(Integer.parseInt(id)).get();

            p.setNom((String) request.getParameter("nom"));
            p.setPrenom((String) request.getParameter("prenom"));
            p.setTelephone((String) request.getParameter("telephone"));
            p.setEmail((String) request.getParameter("email"));
            p.setPhoto((String) request.getParameter("photo"));


            VilleEntity ville = villeRepository.findById(Integer.parseInt(request.getParameter("ville_depuis_template"))).get();

            p.setVille(ville);
            patientRepository.save(p);
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/patient/all";


            // todo creer un attrinut erreur dans la template qui contient le message faire set dans le catch
        }catch(Exception e){
//            e.printStackTrace();
            errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "patient/add_edit";
        }

    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable String id){

        try {
            patientRepository.deleteById(Integer.parseInt(id));

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/patient/all";
    }

    @RequestMapping("/all")
    public String getAllPatients(Model model){
        model.addAttribute("titre","Liste des patients ");
        model.addAttribute("actionToDoText","Ajouter un nouveau patient par ici ");
        model.addAttribute("actionToDoLink","patient/add");


        List<PatientEntity> patients = (List<PatientEntity>) patientRepository.findAll();
        model.addAttribute("patients" , patients);
        return "patient/list";
    }

}
