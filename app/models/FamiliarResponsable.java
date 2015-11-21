package models;


import org.mongodb.morphia.annotations.Embedded;
import play.data.validation.*;

/**
 * @see  Cliente
 * @author jesus
 */

@Embedded
public class FamiliarResponsable{

    @Required
    public String nombre;

    @Required
    public String domicilio;

    @Required
    public String telefono;
    /*
    * Se encargará de recibir los datos del Familiar Responsable del cliente
    * recibiendo nombre, domicilio y telefono
    */

    public FamiliarResponsable(String nombre, String domicilio, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }



}
