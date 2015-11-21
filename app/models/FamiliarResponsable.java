package models;


import org.mongodb.morphia.annotations.Embedded;
import play.data.validation.*;

/**
 *Entidad que se encarga de guardar los datos del familiar resposable del
 * cliente
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

    public FamiliarResponsable(String nombre, String domicilio, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }



}
