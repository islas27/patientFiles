/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import java.util.Date;
import play.modules.morphia.Blob;
import play.modules.morphia.Model;

/**
 *
 * @author jesus
 */
@Entity
public class HistorialMedico extends Model{

    public String motivoConsulta;
    public String antecedentesFamilires;
    public String higientePersonal;
    public Boolean Embarazo;
    public Integer mes;
    public String rutaClinica;
    public Blob diagrama;
    public String descripcion;
    public Date fecha;
    public Blob evidencia;

    @Reference
    public ExpedienteMedico expediente;

    public HistorialMedico(String motivoConsulta, String antecedentesFamilires, String higientePersonal, Boolean Embarazo, Integer mes, String rutaClinica, Blob diagrama, String descripcion, Date fecha, Blob evidencia, ExpedienteMedico expediente) {
        this.motivoConsulta = motivoConsulta;
        this.antecedentesFamilires = antecedentesFamilires;
        this.higientePersonal = higientePersonal;
        this.Embarazo = Embarazo;
        this.mes = mes;
        this.rutaClinica = rutaClinica;
        this.diagrama = diagrama;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.evidencia = evidencia;
        this.expediente = expediente;
    }

    

}
