package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import models.Cita;
import models.Cliente;
import models.Usuario;
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

        Long dias = new Long(0);

        try {
            dias = usuario.caducidadPlan.getTime() - new Date().getTime();
            dias /= 1000;
            dias /= 3600;
            dias /= 24;
        } catch (Exception ex) {

        }

        render(pacientes, hojas, dias, citas, usuario, nCitas);
    }

    public static void listPatients() {
        render();
    }

    public static void myProfile() {
        render();
    }
}
