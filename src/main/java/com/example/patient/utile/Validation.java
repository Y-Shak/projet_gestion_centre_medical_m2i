package com.example.patient.utile;

import com.example.patient.exceptions.InvalidFiledData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validation {

    public static void isDataPatientValide(String nom, String prenom, String telephone, String email, String photo, String ville) throws InvalidFiledData {
        if (nom.length() < 2){
            throw new InvalidFiledData("Valeur du champ nom est invalid, au moins 2 caractère");
        }
        if (prenom.length() < 2){
            throw new InvalidFiledData("Valeur du champ prenom est invalid, au moins 2 caractère");
        }
        if (telephone.length() < 10){
            throw new InvalidFiledData("Valeur du champ telephone est invalid, au moins 10 caractère");
        }
        // validation de la syntaxe de l'email
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            System.out.println("email valide");
        }else{
            throw new InvalidFiledData("Email invalid, veuillez respecter cette syntaxe something@somthing");

        }
    }
    public static void isDataVilleValide(String nom, int codeConverted) throws InvalidFiledData{
        if(nom.length() < 2){
            throw new InvalidFiledData("Valeur du champ nom est invalid, au moins 2 caractère");
        }

    }

}
