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
 *
 * @author jesus
 */
@Entity
public class Proceso extends Model{

    public String descripcion;

    public Blob multimedia;

    @Reference
    public Proceso siguiente;

    @Reference
    public Cita cita;

    public String toString(){
        return this.descripcion.substring(30);
    }

}
