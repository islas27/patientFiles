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
 * @author jesus
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

    public ExpedienteMedico(Cliente paciente, FamiliarResponsable familiarResponsable) {
        this.paciente = paciente;
        this.familiarResponsable = familiarResponsable;
        adicciones = new ArrayList();
        inmunizaciones = new ArrayList();
        patologicos = new ArrayList();
    }


}
