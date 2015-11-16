package models;

import com.google.code.morphia.annotations.Entity;
import java.util.*;

import play.data.validation.*;
import play.modules.morphia.Model;

/**
 *
 * @author jesus
 */

@Entity
public class Cliente extends Model{

    @Required
    public String nombre;

    @Required
    public String apellidoPaterno;

    @Required
    public String apellidoMaterno;

    @Required
    public Date fechaNacimiento;

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

    public String correo;

    public String doctor;

    public List<String> referidos;
}
