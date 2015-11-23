/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
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

    public String inicio;

    public String fin;

    public static List<Cita> getCitasByDoctorAndDate(String doctor, LocalDate today){
        LocalDate tomorrow = today.plusDays(1);
        return getCitasByDoctor(doctor).stream()
                .filter(c -> {return c.getInicio().toLocalDate().isAfter(today) && c.getFin().toLocalDate().isBefore(tomorrow);})
                .collect(Collectors.toList());
    }

    public static List<Cita> getCitasByCliente(Cliente cliente){
        return Cita.find("paciente", cliente).asList();
    }

    public static List<Cita> getCitasByDoctor(String doctor){
        return Cita.filter("doctor", doctor).asList();
    }

    public LocalDateTime getInicio(){
        return LocalDateTime.parse(this.inicio, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }

    public void  setInicio(LocalDateTime date){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.inicio = date.format(formater);
    }

    public LocalDateTime getFin(){
        return LocalDateTime.parse(this.fin, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }

    public void  setFin(LocalDateTime date){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.fin = date.format(formater);
    }

    @Override
    public String toString(){
        return String.format("%s %s %s", inicio.toString(),
                doctor.toString(), paciente.toString());
    }

}
