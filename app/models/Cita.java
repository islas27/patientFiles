/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Calendar;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import java.util.Date;
import java.util.List;
import play.modules.morphia.Model;

/**
 *
 * @author jesus
 */
@Entity
public class Cita extends Model{

    @Reference
    public Cliente paciente;

    @Reference
    public Proceso proceso;

    @Reference
    public HistorialMedico hm;

    public String doctor;

    public Date inicio;

    public Date fin;

    public static List<Cita> getCitasByDoctorAndDate(String doctor, Date date){
        Date date2;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        calendario.roll(Calendar.DATE, true);
        date2 = calendario.getTime();
        return Cita.q().filter(" inicio > ", date).filter("fin < ", date2).asList();
    }

    public static List<Cita> getCitasByCliente(Cliente cliente){
        return Cita.find("paciente", cliente).asList();
    }

}
