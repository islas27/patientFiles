/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Date;
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

    public Date birthday;

    //public Blob foto;

    @Reference
    public Rol rol;

    public  Date caducidadPlan;

    public static Usuario ByEmail(String email){
        return Usuario.find("byEmail", email).first();
    }

    public static Usuario ByEmailAndPassword(String email, String password){
        return Usuario.find("byEmailAndPassword", email, password).first();
    }

    public String toString(){
        return this.email;
    }

}
