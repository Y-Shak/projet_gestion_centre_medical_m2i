package com.example.patient.api;

import com.example.patient.entities.PatientEntity;
import com.example.patient.exceptions.ErrorChargingData;
import com.example.patient.exceptions.UnknownErrorCauses;
import com.example.patient.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/patients")
public class PatientControllerAPI {

    @Autowired
    PatientService patientService;

    @GetMapping(path = "",produces = "application/json")
    public List<PatientEntity> getAllPatients(){
        try {
            return patientService.findAllPatients();
        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
