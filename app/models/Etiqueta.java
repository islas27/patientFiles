/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 *
 * @author jesus
 */
@Entity
public class Etiqueta extends Model{

    public static enum Tags{
        Patologico, Vicio, Inmunizacion

    }

    public String name;
    public String label;
    public String tipo;

    public Etiqueta (String name, String label, Tags tipo){
        this.name = name;
        this.label = label;
        switch (tipo) {
            case Patologico:
                this.tipo = "Patologico";
                break;
            case Vicio:
                this.tipo = "Adicciones";
                break;
            case Inmunizacion:
                this.tipo = "Inmunizacion";
                break;
            default:
                throw new AssertionError();
        }
    }

}
