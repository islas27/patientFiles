package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.mongodb.morphia.annotations.Entity;
import java.util.*;

import play.data.validation.*;
import play.modules.morphia.Model;

/**
 * Modelo que registrará al Cliente/Paciente
 *
 */

@Entity
public class Cliente extends Model{

    /**
    * Campos obligatorios para el registro del cliente
     */
    @Required
    public String nombre;

    @Required
    public String apellidoPaterno;

    @Required
    public String apellidoMaterno;

    @Required
    public String fechaNacimiento;

    @Required
    public String pais;

    @Required
    public String estado;

    @Required
    public String ciudad;

    @Required
    public Boolean esHombre;

    @Required
    public String ocupacion;

    @Required
    public String estadoCivil;

    @Required
    public String domicilio;

    @Required
    public String colonia;

    @Required
    public String telefono;

    @Required
    public String correo;

    @Required
    public String doctor;

    public List<String> referidos;

    public static List<Cliente> getPacientes(String doctor){
        return Cliente.find("doctor", doctor).asList();
    }

    public static List<Cliente> getReferidos(String doctor){
        return Cliente.q().filter("referidos elem", doctor).asList();
    }

    public LocalDateTime getfechaNacimientoDate(){
        return LocalDateTime.parse(this.fechaNacimiento, DateTimeFormatter.ISO_DATE_TIME);
    }

    public void  setfechaNacimientoDate(LocalDateTime date){
        DateTimeFormatter formater = DateTimeFormatter.ISO_DATE_TIME;
        this.fechaNacimiento = date.format(formater);
    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
