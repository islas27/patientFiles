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
public class Etiqueta extends Model{

    public String name;
    public String label;
    public String tipo;

    public Etiqueta (String name, String label, String tipo){
        /*
        *Aqui se alojarán las etiquetas donde se comprobará
        *que cada 'tipo' seleccionado sea de los existentes por haber
        */
        this.name = name;
        this.label = label;
        this.tipo = tipo;

    }

    @Override
    public String toString (){
        return this.name;
    }

}
