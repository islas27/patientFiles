/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.crud;

import controllers.CRUD;
import controllers.Check;
import controllers.Secure;
import play.mvc.With;

/**
 *
 * @author jesus
 */
@With(Secure.class)
@Check("administrador")
public class Rols extends CRUD{

}
