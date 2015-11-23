/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import java.util.ArrayList;
import java.util.List;
import play.modules.morphia.Model;

/**
 *
 * Modelo que relaciona los datos del Cliente, con las adicciones, inmunizaciones, patologicos y crea un expediente
 */
@Entity
public class ExpedienteMedico extends Model{

    @Reference
    public Cliente paciente;

    @Embedded
    public FamiliarResponsable familiarResponsable;

    public List<String> adicciones;

    public List<String> inmunizaciones;

    public List<String> patologicos;
    /*
    *Crea listas, que en otra clase servirán para la elección de sus valores
    */

    public ExpedienteMedico(Cliente paciente, FamiliarResponsable familiarResponsable) {
        /*
        *Crea arreglos para usar los valores de las clases Cliente/paciente - FamiliarResponsable
        */
        this.paciente = paciente;
        this.familiarResponsable = familiarResponsable;
        adicciones = new ArrayList();
        inmunizaciones = new ArrayList();
        patologicos = new ArrayList();
    }

    public String toString(){
        return String.format("EM %s", this.paciente.toString());
    }


}
