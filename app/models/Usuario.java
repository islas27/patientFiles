/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @author jesus
 */
@Entity
public class Usuario extends Model{

    @Column(unique = true)
    public String email;

    public String name;

    @Column(name="last_name")
    public String lastName;

    public String password;

    public Date birthday;

    @ManyToOne
    public Rol rol;

    public static Usuario ByEmail(String email){
        return Usuario.find("byEmail", email).first();
    }

    public static Usuario ByEmailAndPassword(String email, String password){
        return Usuario.find("byEmailAndPassword", email, password).first();
    }

}
