/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.ArrayList;
import java.util.List;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.ManyToAny;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class Rol extends Model
{
    @Column(nullable = false)
    public String roleName;

    @ManyToMany
    public List<Permiso> permisos;

}
