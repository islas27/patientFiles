package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.time.Period;
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
        if (Seguridad.isConnected()) {
            Usuario usuario = Usuario.find("byEmail", Seguridad.connected()).first();
            renderArgs.put("usuario", usuario);
        }
    }

    public static void index() {
        LocalDate hoy = LocalDate.now();
        Usuario usuario = Usuario.ByEmail(Seguridad.connected());
        Long pacientes = Cliente.getPacientes(usuario.email).stream().count();
        List<Cita> citas = Cita.getCitasByDoctorAndDate(usuario.email, hoy);
        Long nCitas = Cita.getCitasByDoctor(usuario.email).stream().count();
        int hojas = 0;
        hojas += citas.size();
        hojas += pacientes * 4;
        List<Cliente> lista = Cliente.getPacientes(Seguridad.connected());
        Long dias;

        if (usuario.getCaducidadPlanDate() == null) {
            dias = 0L;
        } else {
            dias = ChronoUnit.DAYS.between(hoy, usuario.getCaducidadPlanDate());
            //dias = Period.between(hoy, usuario.getCaducidadPlanDate()).getMonths();
        }
        render(pacientes, hojas, dias, citas, nCitas, lista);
    }

    public static void listPatients() {
        List<Cliente> lista = Cliente.getPacientes(Seguridad.connected());
        render(lista);
    }

    public static void myProfile() {
        Usuario usuario = Usuario.ByEmail(Seguridad.connected());
        Long npac = Cliente.getPacientes(usuario.email).stream().count();
        Long ncit = Cita.getCitasByDoctor(usuario.email).stream().count();
        Long nhoj = ncit  + npac*4;
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd MM yyyy");
        //String caducidad = usuario.getCaducidadPlanDate().format(formater);
        String registro = "22 11 2015";
        render(npac, ncit, nhoj, /*caducidad,*/ registro);
    }

    public static void newPatient() {
        List<Etiqueta> inmunizaciones = Etiqueta.find("tipo", "Inmunizacion").asList();
        List<Etiqueta> patologicos = Etiqueta.find("tipo", "Patologico").asList();
        List<Etiqueta> adicciones = Etiqueta.find("tipo", "Adiccion").asList();
        render(patologicos, inmunizaciones, adicciones);
    }

    public static void createPatient(String nombre, String apellidoPaterno,
            String apellidoMaterno, String fechaNac, String paisNac,
            String estadoNac, String ciudadNac, String sexo, String estadoCivil,
            String ocupacion, String domicilio, String colonia, String telefono,
            String fnombre, String fdomicilio, String ftelefono,
            String motivoConsulta, String antecedentesFam, String higieneGral,
            String embarazo, String trimestre, List<String> inmunizaciones,
            List<String> vicios, List<String> antecedentes) {
        Cliente paciente = new Cliente();
        FamiliarResponsable fr = new FamiliarResponsable(fnombre, fdomicilio, ftelefono);
        ExpedienteMedico em = new ExpedienteMedico(paciente, fr);
        HistorialMedico hm = new HistorialMedico(motivoConsulta, antecedentesFam, higieneGral, embarazo, trimestre, localDate.now(em, Seguridad.connected()));

        paciente.nombre = nombre;

        paciente.apellidoMaterno = apellidoMaterno;
        paciente.apellidoPaterno = apellidoPaterno;
        paciente.ciudad = ciudadNac;
        paciente.colonia =  colonia;
        paciente.doctor = Seguridad.connected();
        paciente.setfechaNacimientoDate( LocalDate.parse(fechaNac, DateTimeFormatter.ofPattern("MM/dd/yyyy") ) );
        paciente.estado = estadoNac;
        paciente.esHombre = sexo.equals("M");
        paciente.pais = paisNac;
        paciente.estadoCivil = estadoCivil;
        paciente.ocupacion = ocupacion;
        paciente.domicilio = domicilio;
        paciente.telefono = telefono;
        em.inmunizaciones = inmunizaciones;
        em.patologicos = antecedentes;
        em.adicciones = vicios;
        paciente.save();
        em.save();

        newPatient();
    }

    public static void newAppointment(String paciente, String razon, String fecha, String inittime, String endtime) {
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
        Cliente paciente = Cliente.findById(id);
        ExpedienteMedico em = ExpedienteMedico.find("paciente", paciente).first();
        Integer edad = Period.between(paciente.getfechaNacimientoDate(), LocalDate.now()).getYears();
        render(em, paciente, edad);
    }

    public static void newProcedure(){
        render();
    }
}
