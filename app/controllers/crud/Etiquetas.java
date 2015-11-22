/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.crud;

import controllers.CRUD;
import controllers.Secure;
import controllers.Check;
import play.mvc.With;


/**
*Controlador que se encarga de enlazar el modelo Etiquetas al crud
*@see models.Etiqueta
 */
@With(Secure.class)
@Check("administrador")
public class Etiquetas extends CRUD{

}
