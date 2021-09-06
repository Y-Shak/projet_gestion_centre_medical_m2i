package com.example.patient.services;

import com.example.patient.entities.VilleEntity;
import com.example.patient.exceptions.ErrorChargingData;
import com.example.patient.repositories.VilleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public List<VilleEntity> findAllVilles() throws ErrorChargingData {
        try {
//                faire ça pour debuger cette exception
//            Integer.parseInt("ddd");
            return (List<VilleEntity>) villeRepository.findAll();

        }catch (Exception e){
            throw new ErrorChargingData(" Erreur lors du chargement des données sur les villes , actualisez la page ");
        }

    }
}
