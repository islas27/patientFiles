package controllers;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import models.Cita;
import models.Cliente;
import models.Etiqueta;
import models.ExpedienteMedico;
import models.FamiliarResponsable;
import models.Proceso;
import models.Usuario;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by islas on 11/16/15.
 */

/**
 * Controlador de la ventana para miembro usuario de la pagina
 */
@With(Secure.class)
@Check("Member")
public class Members extends Controller {

    public static void index() {
        LocalDate hoy = LocalDate.now();
        Usuario usuario = Usuario.ByEmail(Seguridad.connected());
        Long pacientes = Cliente.getPacientes(usuario.email).stream().count();
        List<Cita> citas = Cita.getCitasByDoctorAndDate(usuario.email, hoy);
        Long hojas = Cita.getCitasByDoctor(usuario.email).stream()
                .filter(c -> c.proceso != null).count();
        Long nCitas = hojas;
        hojas += citas.size();
        hojas += pacientes * 4;
        List<Cliente> lista = Cliente.getPacientes(Seguridad.connected());
        usuario.setCaducidadPlanDate(LocalDate.of(2015, Month.DECEMBER, 23));

        Long dias;

        if (usuario.getCaducidadPlanDate() == null) {
            dias = 0L;
        }else{
            dias = ChronoUnit.DAYS.between(hoy, usuario.getCaducidadPlanDate());
            //dias = Period.between(hoy, usuario.getCaducidadPlanDate()).getMonths();
        }
        render(pacientes, hojas, dias, citas, usuario, nCitas, lista);
    }

    public static void listPatients() {
        List<Cliente> lista = Cliente.getPacientes(Seguridad.connected());
        render(lista);
    }

    public static void myProfile() {
        render();
    }

    public static void newPatient() {
        List<Etiqueta> inmunizaciones = Etiqueta.find("tipo", "Inmunizacion").asList();
        List<Etiqueta> patologicos = Etiqueta.find("tipo", "Patologico").asList();
        List<Etiqueta> adicciones = Etiqueta.find("tipo", "Adiccion").asList();
        Cliente paciente = new Cliente();
        FamiliarResponsable fr = new FamiliarResponsable(null, null, null);
        ExpedienteMedico em = new ExpedienteMedico(paciente, fr);
        render(em, patologicos, inmunizaciones, adicciones);
    }

    public static void newAppointment(String pacienteId,
            @Required(message = "Es requerida una descripcion") String descripcion/*,
            Date inicio, Date fin*/) {
//
//        if (Cita.getCitasByDoctor(Seguridad.connected()).stream()
//                .filter(c -> {
//                    return (c.inicio.after(inicio) && c.fin.before(inicio))
//                    || (c.inicio.after(fin) && c.fin.before(fin));
//                }).count() > 0) {
//            flash.error("Las citas se empalman");
//            render("/members/index");
//        }
//        if (validation.hasErrors()) {
//            render("/members/index");
//        }
//
//        Cita cita = new Cita();
//        cita.doctor = Seguridad.connected();
//        cita.paciente = Cliente.findById(pacienteId);
//        cita.inicio = inicio;
//        cita.fin = fin;
//        cita.proceso = new Proceso();
//
//        cita.save();
//        flash.success("Todo bien");
//        index();
    }

    public static void patient(){
        render();
    }
}
