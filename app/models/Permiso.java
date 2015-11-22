/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import org.mongodb.morphia.annotations.Entity;
import play.modules.morphia.Model;


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

    public String toString(){
        return this.nombre;
    }

}
