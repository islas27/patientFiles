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

    /*
    En esta entidad Etiqueta estarán las clases para la comprobacion de etiquetas
    en la inserción de valores
     */
    public static enum Tags{
        /*
        Aqui se enumeraran las tags de todo el proyecto por utilizar
         */
        Patologico, Vicio, Inmunizacion

    }


    public String name;
    public String label;
    public String tipo;

    public Etiqueta (String name, String label, Tags tipo){
        /*
        *Aqui se alojarán las etiquetas donde se comprobará
        *que cada 'tipo' seleccionado sea de los existentes por haber
        */
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
