/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Permiso;
import models.Usuario;

/**
 * Controlador que
 */
public class Seguridad extends Secure.Security {

    static boolean authenticate(String username, String password) {
        Usuario user = Usuario.find("byEmail", username).first();
        return user != null && user.password.equals(password);
    }

    static boolean check(String profile) {
        Usuario user = Usuario.ByEmail(Seguridad.connected());
        return user.rol.permisos.contains(new Permiso(profile));
    }

    static void onDisconnected() {
        Application.index();
    }

    static void onAuthenticated() {
        Usuario user = Usuario.ByEmail(Seguridad.connected());
        if (user.rol.permisos.contains(new Permiso("administrador"))) {
            CRUD.index();
        } else {
            if (user.rol.permisos.contains(new Permiso("Member"))) {
                Members.index();
            } else {
                Application.index();
            }
        }
        
    }

}
