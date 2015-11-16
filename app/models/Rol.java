/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.ArrayList;
import java.util.List;
import models.deadbolt.Role;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.ManyToAny;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class Rol extends Model implements Role
{
    @Column(nullable = false)
    public String roleName;

    @ManyToMany
    public List<Permiso> permisos;

    public Rol(String roleName)
    {
        this.roleName = roleName;
        permisos = new ArrayList<Permiso>();
    }

    public String getRoleName()
    {
        return roleName;
    }
}
