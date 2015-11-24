package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

/**
 *Controlador de paginas públicas
 */
public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void plansPayments(){
        render();
    }

    public static void newDentist(){
        render();
    }

    public static void saveUser(String email, String password, String nombre, String birthday){
        Usuario usuario = new Usuario();
        usuario.email = email;
        usuario.name = nombre;
        usuario.password = password;
        usuario.lastName = "";
        usuario.rol = Rol.find("rolName", "Free").first();
        usuario.setBirthdayDate(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        usuario.save();
        Seguridad.authenticate(email, password);
    }

    public static void testing(){
        render();
    }

}