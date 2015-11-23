/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import play.modules.morphia.Model;


/**
 * Modelo que hace referencia a los permisos de los usuarios
 */
@Entity
public class Rol extends Model
{
    public String roleName;

    @Reference
    public List<Permiso> permisos;

    public String toString (){
        return this.roleName;
    }

}
