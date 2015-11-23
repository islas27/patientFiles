package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import models.Cita;
import models.Cliente;
import models.Proceso;
import models.Usuario;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by islas on 11/16/15.
 */
@With(Secure.class)
@Check("Member")
public class Members extends Controller {

    public static void index() {
        Date hoy = new Date();
        Usuario usuario = Usuario.ByEmail(Seguridad.connected());
        Long pacientes = Cliente.getPacientes(usuario.email).stream().count();
        List<Cita> citas = Cita.getCitasByDoctorAndDate(usuario.email, hoy);
        Long hojas = Cita.getCitasByDoctor(usuario.email).stream()
                .filter(c -> c.proceso != null).count();
        Long nCitas = hojas;
        hojas += citas.size();
        hojas += pacientes * 4;
        List<Cliente> lista = Cliente.getPacientes(Seguridad.connected());

        Long dias = new Long(0);

        try {
            dias = usuario.caducidadPlan.getTime() - new Date().getTime();
            dias /= 1000;
            dias /= 3600;
            dias /= 24;
        } catch (Exception ex) {

        }

        render(pacientes, hojas, dias, citas, usuario, nCitas, lista);
    }

    public static void listPatients() {
        render();
    }

    public static void myProfile() {
        render();
    }

    public static void newPatient() {
        render();
    }

    public static void newAppointment(String pacienteId,
            @Required(message = "Es requerida una descripcion") String descripcion,
            Date inicio, Date fin) {

        if (Cita.getCitasByDoctor(Seguridad.connected()).stream()
                .filter(c -> {
                    return (c.inicio.after(inicio) && c.fin.before(inicio))
                    || (c.inicio.after(fin) && c.fin.before(fin));
                }).count() > 0) {
            flash.error("Las citas se empalman");
            render("/members/index");
        }
        if (validation.hasErrors()) {
            render("/members/index");
        }

        Cita cita = new Cita();
        cita.doctor = Seguridad.connected();
        cita.paciente = Cliente.findById(pacienteId);
        cita.inicio = inicio;
        cita.fin = fin;
        cita.proceso = new Proceso();

        cita.save();
        flash.success("Todo bien");
        index();
    }
}
