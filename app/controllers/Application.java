package controllers;

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

   /*public static void saveUser(Usuario usr){
        usr.save();
        Security.authenticate(usr.email, usr.password);
        MembersArea.index();
    }*/

}