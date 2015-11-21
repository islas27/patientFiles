/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import play.db.jpa.Model;

/**
 *
 * @author jesus
 */
@Entity
public class Permiso extends Model{
    public String nombre;

    public Permiso(String nombre){
        this.nombre = nombre;
    }

}
