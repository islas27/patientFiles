/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import java.util.Date;
import play.modules.morphia.Blob;
import play.modules.morphia.Model;

/**
 * Modelo que se encargará de guardar los datos del historial medico del cliente
 * que se agregará con cada cita que tenga el paciente con el doctor
 */
@Entity
public class HistorialMedico extends Model{

    public String motivoConsulta;
    public String antecedentesFamilires;
    public String higientePersonal;
    public Boolean Embarazo;
    public Integer trimestre;
//    public String rutaClinica;
//    public Blob diagrama;
//    public String descripcion;
    public String fecha;
//    public Blob evidencia;

    @Reference
    public ExpedienteMedico expediente;


//    @Reference
//    public Proceso primerProceso;

    public String doctor;

    public HistorialMedico(String motivoConsulta, String antecedentesFamilires, String higientePersonal, Boolean Embarazo, Integer trimestre, String descripcion, LocalDate fecha, ExpedienteMedico expediente, String doctor) {
        this.motivoConsulta = motivoConsulta;
        this.antecedentesFamilires = antecedentesFamilires;
        this.higientePersonal = higientePersonal;
        this.Embarazo = Embarazo;
        this.trimestre = trimestre;
        this.setFechaDate(fecha);
        this.expediente = expediente;
        this.doctor = doctor;
    }

    public String toString(){
        return this.motivoConsulta;
    }

    public LocalDate getFechaDate(){
        try{
            return LocalDate.parse(this.fecha, DateTimeFormatter.ISO_DATE);
        }catch (Exception ex) {
            return null;
        }
    }

    public void  setFechaDate(LocalDate date){
        DateTimeFormatter formater = DateTimeFormatter.ISO_DATE;
        this.fecha = date.format(formater);
    }

}
