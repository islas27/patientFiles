/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.crud;

import play.mvc.*;
import controllers.Check;
import controllers.CRUD;
import controllers.Secure;


/**
*Controlador que se encarga de enlazar el modelo Usuarios al crud
*@see models.Usuario
 */
@With(Secure.class)
@Check("administrador")
public class Usuarios extends CRUD{

}
