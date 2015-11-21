/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import java.util.ArrayList;
import java.util.List;
import play.modules.morphia.Model;

/**
 *
 * @author jesus
 */
@Entity
public class ExpedienteMedico extends Model{

    @Reference
    public Cliente paciente;
    /*
    *Hace referencia a la clase de Cliente llamandola paciente para su uso proximo
     */

    @Embedded
    public FamiliarResponsable familiarResponsable;
    /*
    *Hace una copia del objeto FamiliarResponsable para el uso en esta clase
    *de sus valores
    */

    public List<String> adicciones;

    public List<String> inmunizaciones;

    public List<String> patologicos;
    /*
    *Crea dentro de esta clase 3 listas nuevas, que en otra clase servirán para
    *la elección de sus valores
    */

    public ExpedienteMedico(Cliente paciente, FamiliarResponsable familiarResponsable) {
        /*
        *Crea arreglos para usar los valores de la clase Cliente/paciente y de la clase
        *FamiliarResponsable
        */
        this.paciente = paciente;
        this.familiarResponsable = familiarResponsable;
        adicciones = new ArrayList();
        inmunizaciones = new ArrayList();
        patologicos = new ArrayList();
    }


}
