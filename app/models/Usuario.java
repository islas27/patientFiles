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

    public Blob foto;

    @Reference
    public Rol rol;

    public String caducidadPlan;

    public static Usuario ByEmail(String email){
        return Usuario.find("byEmail", email).first();
    }

    public static Usuario ByEmailAndPassword(String email, String password){
        return Usuario.find("byEmailAndPassword", email, password).first();
    }

    public LocalDate getBirthday(){
        return LocalDate.parse(this.birthday, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }

    public void  setBirthday(LocalDate date){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.birthday = date.format(formater);
    }

    public LocalDate getCaducidadPlan(){
        return LocalDate.parse(this.caducidadPlan, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }

    public void  setCaducidadPlan(LocalDate date){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.caducidadPlan = date.format(formater);
    }

    public String toString(){
        return this.email;
    }

}
