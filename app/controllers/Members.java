package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by islas on 11/16/15.
 */
@With(Secure.class)
@Check("Member")
public class Members extends Controller {

    public static void index(){
        render();
    }

    public static void listPatients(){
        render();
    }

    public static void myProfile(){
        render();
    }

    public static void newPatient(){
        render();
    }
}
