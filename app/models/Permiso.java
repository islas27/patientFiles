/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Objects;
import org.mongodb.morphia.annotations.Entity;
import play.modules.morphia.Model;


/**
 * Modelo que administra los permisos de los Usuarios
 */
@Entity
public class Permiso extends Model{
    public String nombre;

    public Permiso(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return this.nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permiso other = (Permiso) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    

}
