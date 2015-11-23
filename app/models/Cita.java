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
import java.util.stream.Collectors;
import play.modules.morphia.Model;

/**
 * Modelo que obtiene las Citas realizadas por el Doctor
 *
 */
@Entity
public class Cita extends Model{

    @Reference
    public Cliente paciente;

    @Reference
    public Proceso proceso;

    public String descripcion;

    public String doctor;

    public Date inicio;

    public Date fin;

    public static List<Cita> getCitasByDoctorAndDate(String doctor, Date date){
        return getCitasByDoctor(doctor).stream().filter(c -> c.inicio.before(date))
                .collect(Collectors.toList());
    }

    public static List<Cita> getCitasByCliente(Cliente cliente){
        return Cita.find("paciente", cliente).asList();
    }

    public static List<Cita> getCitasByDoctor(String doctor){
        return Cita.filter("doctor", doctor).asList();
    }

    @Override
    public String toString(){
        return String.format("%s %s %s", inicio.toString(),
                doctor.toString(), paciente.toString());
    }

}
