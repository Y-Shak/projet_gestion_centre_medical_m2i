package com.example.patient.services;

import com.example.patient.entities.VilleEntity;
import com.example.patient.exceptions.*;
import com.example.patient.repositories.VilleRepository;
import com.example.patient.utile.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VilleService {

    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public List<VilleEntity> findAllVilles() throws ErrorChargingData, UnknownErrorCauses {
        try {
//                faire ça pour debuger cette exception
//            Integer.parseInt("ddd");
            return (List<VilleEntity>) villeRepository.findAll();

        }catch (IllegalArgumentException e1){
            throw new ErrorChargingData(" Erreur lors du chargement des données sur les villes , actualisez la page ");
        }catch (Exception e2){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }

    }
    public VilleEntity findById(int id) throws ErrorChargingOneElementData, UnknownErrorCauses {

        try {
            return  villeRepository.findById(id).get();
        }catch (IllegalArgumentException e){
            throw new ErrorChargingOneElementData(" Erreur lors du chargement des données sur la ville sélectionné ");
        }catch (Exception e2){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }
    }

    public void deleteVille(int id ) throws Exception{

        try {
            villeRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            throw new ErrorWhileDeletingElement(" Erreur lors de la suppression du patient");
        }catch (Exception e2){
            throw new UnknownErrorCauses(" Erreur inconnue. ");
        }
    }

    @Transactional
    public void addVille(String nom , String code) throws UnknownErrorCauses, SavingElementFailed, InvalidFiledData, UnsupportedValueOfIdDetected {

        try{
            int codeConverted = Integer.parseInt(code) ;
            Validation.isDataVilleValide(nom,codeConverted);
            VilleEntity ville = new VilleEntity();
            ville.setNom(nom);
            ville.setCodePostal(codeConverted);
            villeRepository.save(ville);
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
    public void editVille(int id, String nom , String code) throws UnknownErrorCauses, SavingElementFailed, InvalidFiledData, UnsupportedValueOfIdDetected{
        try{
            int codeConverted = Integer.parseInt(code) ;
            Validation.isDataVilleValide(nom,codeConverted);
            VilleEntity ville = villeRepository.findById(id).get();
            ville.setNom(nom);
            ville.setCodePostal(codeConverted);
            villeRepository.save(ville);
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
}
