package com.example.patient.services;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.VilleEntity;
import com.example.patient.exceptions.*;
import com.example.patient.repositories.PatientRepository;
import com.example.patient.repositories.VilleRepository;
import com.example.patient.utile.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final VilleRepository villeRepository;

    public PatientService(PatientRepository patientRepository, VilleRepository villeRepository) {
        this.patientRepository = patientRepository;
        this.villeRepository = villeRepository;
    }

    public List<PatientEntity> findAllPatients() throws ErrorChargingData, UnknownErrorCauses {

        try {
//                faire ça pour debuger cette exception
//            Integer.parseInt("ddd");
            return  (List<PatientEntity>) patientRepository.findAll();
        }catch (IllegalArgumentException e1){
            throw new ErrorChargingData(" Erreur lors du chargement des données sur les patients. ");
        }catch (Exception e2){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }
    }

    public PatientEntity findPatientById(int id ) throws ErrorChargingOneElementData, UnknownErrorCauses {
        try {
            // faire ça pour debuger cette exception
//            Integer.parseInt("ddd");
            return  patientRepository.findById(id).get();
        }catch (IllegalArgumentException e){
            throw new ErrorChargingOneElementData(" Erreur lors du chargement des données sur le patient sélectionné ");
        }catch (Exception e2){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }

    }
    public void deletePatient(int id) throws Exception{
        try {
            patientRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            throw new ErrorWhileDeletingElement(" Erreur lors de la suppression du patient");
        }catch (Exception e2){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }
    }


    @Transactional
    public void addPatient(String nom, String prenom, String telephone, String email, String photo, String ville) throws UnsupportedValueOfIdDetected, SavingElementFailed, UnknownErrorCauses, InvalidFiledData {

        try {
//            isDataPatientValide(nom, prenom, telephone, email, photo, ville);
            Validation.isDataPatientValide(nom, prenom, telephone, email, photo, ville);
            PatientEntity p = new PatientEntity();
            VilleEntity villeEntity = new VilleEntity();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setTelephone(telephone);
            p.setEmail(email);
            p.setPhoto(photo);
            villeEntity.setId(Integer.parseInt(ville));
            p.setVille(villeEntity);
            // faire ça pour debuger cette exception et voir le comportement de l'app
//            p=null;
            patientRepository.save(p);
        }catch (NumberFormatException e){
                throw new UnsupportedValueOfIdDetected("Selectionnez une ville valide depuis la liste et réessayer");
        }catch (IllegalArgumentException e1){
                throw  new SavingElementFailed("Impossible d'enregistrer pour le moment réessayer plus tard");
        }catch (InvalidFiledData e2){
            throw e2;
        } catch (Exception e3){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }

    }


    @Transactional
    public void editPatient(int id, String nom, String prenom, String telephone, String email, String photo, String ville) throws UnsupportedValueOfIdDetected, SavingElementFailed, UnknownErrorCauses, InvalidFiledData {
        try{
            Validation.isDataPatientValide(nom, prenom, telephone, email, photo, ville);
            PatientEntity p = patientRepository.findById(id).get();
            VilleEntity villeEntity = new VilleEntity();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setTelephone(telephone);
            p.setEmail(email);
            p.setPhoto(photo);
            villeEntity.setId(Integer.parseInt(ville));
            p.setVille(villeEntity);
            patientRepository.save(p);
        }catch (NumberFormatException e){
            throw new UnsupportedValueOfIdDetected("Selectionnez une ville valide depuis la liste et réessayer");
        }catch (IllegalArgumentException e1){
            throw  new SavingElementFailed("Impossible de modifier pour le moment réessayer plus tard");
        }catch (InvalidFiledData e2){
            throw e2;
        } catch (Exception e3){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }

    }
}
