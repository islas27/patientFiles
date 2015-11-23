package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import models.Cita;
import models.Cliente;
import models.Etiqueta;
import models.ExpedienteMedico;
import models.FamiliarResponsable;
import models.Usuario;
import play.mvc.Before;
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

    @Before
    static void setConnectedUser() {
        if(Seguridad.isConnected()) {
            Usuario usuario = Usuario.find("byEmail", Seguridad.connected()).first();
            renderArgs.put("usuario", usuario);
        }
    }

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
        Long dias;

        if (usuario.getCaducidadPlanDate() == null) {
            dias = 0L;
        }else{
            dias = ChronoUnit.DAYS.between(hoy, usuario.getCaducidadPlanDate());
            //dias = Period.between(hoy, usuario.getCaducidadPlanDate()).getMonths();
        }
        System.out.println(Cita.getCitasByDoctor(Seguridad.connected()));
        render(pacientes, hojas, dias, citas, nCitas, lista);
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
        FamiliarResponsable fr = new FamiliarResponsable("", "", "");
        ExpedienteMedico em = new ExpedienteMedico(paciente, fr);
        render(em, patologicos, inmunizaciones, adicciones);
    }

    public static void createPatient(String nombre, String apellidoPaterno,
            String apellidoMaterno, String fechaNac, String paisNac,
            String estadoNac, String ciudadNac, String sexo, String edoCivil,
            String ocupacion, String domicilio, String colonia, String telefono,
            String fnombre, String fdomicilio, String ftelefono,
            String motivoConsulta, String antecedentesFam, String higieneGral,
            String embarazo, String trimestre, String inmunizaciones,
            String vicios,  String antecedentes){

    }

    public static void newAppointment(String paciente,String razon, String fecha, String inittime, String endtime ){
        LocalDateTime inicio = LocalDateTime.parse(fecha + inittime, DateTimeFormatter.ofPattern("MM/dd/yyyyHH:mm"));
        LocalDateTime fin = LocalDateTime.parse(fecha + endtime, DateTimeFormatter.ofPattern("MM/dd/yyyyHH:mm"));

        if (!inicio.isBefore(fin)) {
            flash.error("El inicio debe ir antes del fin");
            index();
        }
        if (Cita.getCitasByDoctor(Seguridad.connected()).stream()
                .filter(c -> {
                    return (c.getInicioDate().isAfter(inicio) && c.getFinDate().isBefore(inicio))
                    || (c.getInicioDate().isAfter(fin) && c.getFinDate().isBefore(fin));
                }).count() > 0) {
            flash.error("Las citas se empalman");
            index();
        }
        if (razon == null) {
            flash.error("Las citas deben tener alguna razon");
            index();
        }
        if (validation.hasErrors()) {
            index();
        }

        Cita cita = new Cita();
        cita.doctor = Seguridad.connected();
        cita.paciente = Cliente.findById(paciente);
        cita.setInicioDate(inicio);
        cita.setFinDate(fin);

        cita.save();
        flash.success("Todo bien");
        index();
    }

    public static void patient(String id){
        render();
    }

    public static void newProcedure(){
        render();
    }
}
