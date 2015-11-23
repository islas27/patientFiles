/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import play.modules.morphia.Blob;
import play.modules.morphia.Model;

/**
 * Modelo que obtiene a los usuarios y hace el debido Login
 */
@Entity
public class Usuario extends Model{

    public String email;

    public String name;

    public String lastName;

    public String password;

    public String birthday;

//    public Blob foto;

    @Reference
    public Rol rol;

    public String caducidadPlan;

    public static Usuario ByEmail(String email){
        return Usuario.find("byEmail", email).first();
    }

    public static Usuario ByEmailAndPassword(String email, String password){
        return Usuario.find("byEmailAndPassword", email, password).first();
    }

    public LocalDate getBirthdayDate(){
        try{
            return LocalDate.parse(this.birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        }catch (Exception ex) {
            return null;
        }
    }

    public void  setBirthdayDate(LocalDate date){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.birthday = date.format(formater);
    }

    public LocalDate getCaducidadPlanDate(){
        if (this.caducidadPlan == null) {
            return null;
        }
        try{
        return LocalDate.parse(this.caducidadPlan, DateTimeFormatter.ofPattern("yyyyMMdd"));
        }catch (Exception ex) {
            return null;
        }
    }

    public void  setCaducidadPlanDate(LocalDate date){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.caducidadPlan = date.format(formater);
    }



    public String toString(){
        return this.email;
    }

}
