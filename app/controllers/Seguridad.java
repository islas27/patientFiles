/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import models.Permiso;
import models.Usuario;

/**
 *
 * @author jesus
 */
public class Seguridad extends Secure.Security{

    static boolean authenticate(String username, String password) {
        Usuario user = Usuario.find("byEmail", username).first();
        return user != null && user.password.equals(password);
    }

    static boolean check(String profile) {
        Usuario user = Usuario.ByEmail(Seguridad.connected());
        for (Permiso p : user.rol.permisos) {
            if (p.nombre.equals(profile)) {
                return true;
            }
        }
        return false;
    }

}
