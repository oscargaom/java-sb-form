package com.bolsadeideas.springboot.app.springbootform.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bolsadeideas.springboot.app.springbootform.editors.MayusculasEditor;
import com.bolsadeideas.springboot.app.springbootform.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.app.springbootform.editors.RolePropertyEditor;
import com.bolsadeideas.springboot.app.springbootform.models.domain.Pais;
import com.bolsadeideas.springboot.app.springbootform.models.domain.Role;
import com.bolsadeideas.springboot.app.springbootform.models.domain.Usuario;
import com.bolsadeideas.springboot.app.springbootform.services.PaisService;
import com.bolsadeideas.springboot.app.springbootform.services.RoleService;
import com.bolsadeideas.springboot.app.springbootform.validation.UsuarioValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("user")
public class FormController {

    @Autowired
    private UsuarioValidator validator;

    @Autowired
    private PaisService paisService;

    @Autowired
    private PaisPropertyEditor paisPropertyEditor;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePropertyEditor rolePropertyEditor;
    /*
     * @RequestParam("password") String password. SpringBoot asigna directamente el
     * valor del input del formulario de nombre password en la variable password de
     * tipo String.
     */
    // @PostMapping("/form")
    // public String procesarForm(Model model, @RequestParam("username") String
    // username,
    // @RequestParam("password") String password, @RequestParam String email) {

    // Usuario usuario = new Usuario();

    // usuario.setEmail(email);
    // usuario.setPassword(password);
    // usuario.setUsername(username);

    // model.addAttribute("usuario", usuario);
    // model.addAttribute("titulo", "Parámetros recibidos del formulario");

    // return "resultado";
    // }

    /*
     * Usuario usuario. SpringBoot asigna directamente los valores del input del
     * formulario a cada uno de las variables de la clase (usuario, email y
     * password) de tipo String por medio de sus métodos setters.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        /*
         * Cuando al binder se le hace un setValidator este sobreescribe cualquier
         * validator que pudiera ya existir, por eso lo recomendable es hacer un
         * addValidators, ya que este va agregar a la pila de validators el que nosotros
         * le indiquemos.
         */
        // binder.setValidator(validator);
        binder.addValidators(validator);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*
         * setLenient en false establece que la validación del formato no va a ser
         * tolarante o poco severo
         */
        dateFormat.setLenient(false);
        /*
         * De esta manera la validación se aplica para cualquier valor de tipo Date que
         * llegue desde el formulario
         */
        // binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
        // false));
        /*
         * De esta manera realizamos una validación más especifica del campo que
         * queremos validar
         */
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, "nombre", new MayusculasEditor());
        binder.registerCustomEditor(String.class, "apellido", new MayusculasEditor());
        binder.registerCustomEditor(Pais.class, "paisObject", paisPropertyEditor);
        binder.registerCustomEditor(Role.class, "rolesObj", rolePropertyEditor);
    }

    @ModelAttribute("paises")
    public List<String> registrarPaises() {
        // return Arrays.asList("México", "España", "Colombia", "Argentina", "Peru",
        // "Honduras", "El Salvador");

        return paisService.listar().stream().map(Pais::getNombre).collect(Collectors.toList());
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> registrarPaisesMap() {
        // Map<String, String> paises = new HashMap<>();

        // paises.put("MX", "México");
        // paises.put("ES", "España");
        // paises.put("CO", "Colombia");
        // paises.put("AR", "Argentina");
        // paises.put("PE", "Peru");
        // paises.put("HO", "Honduras");
        // paises.put("EL", "El Salvador");

        // return paises;

        List<Pais> paises = paisService.listar();

        return paises.stream().collect(Collectors.toMap(Pais::getCodigo, Pais::getNombre));
    }

    @ModelAttribute("paisesObj")
    public List<Pais> registrarPaisesObj() {
        return paisService.listar();
    }

    @ModelAttribute("rolesFmtStr")
    public List<String> registrarRolesFmtStr() {

        return roleService.listar().stream().map(Role::getNombre).collect(Collectors.toList());
    }

    @ModelAttribute("rolesFmtMap")
    public Map<String, String> registrarRolesFmtMap() {

        return roleService.listar().stream().collect(Collectors.toMap(Role::getRole, Role::getNombre));
    }

    @ModelAttribute("rolesFmtObj")
    public List<Role> registrarRolesfmtObj() {

        return roleService.listar();
    }

    @ModelAttribute("listaGenero")
    public List<String> registraListaGenero() {

        return Arrays.asList("Femenino", "Masculino");
    }

    @GetMapping("/form")
    public String form(Model model) {
        /*
         * Se agrega el objeto usuario al modelo porque la primera vez que se carga la
         * paǵina con el formulario este aún no existe y genera un error porque es nulo.
         */
        Usuario usuario = new Usuario();
        usuario.setNombre("Federico");
        usuario.setApellido("Aguilar");
        usuario.setIdentity(98011336L);
        usuario.setMatricula("13.456.789-W");
        usuario.setHabilitar(true);
        usuario.setPais(paisService.obtenerPorId(1).getNombre());
        usuario.setPaisMap(paisService.obtenerPorId(2).getCodigo());
        usuario.setPaisObject(paisService.obtenerPorId(3));
        usuario.setRolesObj(Arrays.asList(roleService.obtenerPorId(1)));
        usuario.setRolesStr(Arrays.asList(roleService.obtenerPorId(3).getNombre()));
        usuario.setGenero(registraListaGenero().get(0));
        usuario.setPassword("123");
        usuario.setUsername("oscar");
        usuario.setEmail("email@email.com");
        usuario.setCuenta(5000);
        /* Si se coloca en el form por defautl aparecerá el país indicado */
        // usuario.setPaisObject(new Pais(1, "MX", "México"));
        model.addAttribute("user", usuario);
        model.addAttribute("titulo", "Datos de entrada del formulario");
        return "form";
    }

    /*
     * La anotación @ModelAttribute nos sirve para nombrar el objeto que usaremos
     * entre el controlador y la vista, en caso de no hacerlo tomara por default el
     * nombre del parámetro que en este cas sería usuario y es el que también
     * deberíamos manejar en la vista o página html th:object="${user}".
     */
    @PostMapping("/form")
    // public String procesarForm(@Valid Usuario usuario, BindingResult result,
    // Model model) {
    public String procesarForm(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model) {

        // validator.validate(usuario, result);
        if (result.hasErrors()) {

            model.addAttribute("titulo", "Parámetros recibidos del formulario");

            // Map<String, String> errores = new HashMap<>();

            // result.getFieldErrors().forEach(err -> {
            // errores.put(err.getField(), "El campo ".concat(err.getField()).concat("
            // ").concat(err.getDefaultMessage()));
            // });

            // model.addAttribute("error", errores);

            /*
             * En caso de que ocurra un error Spring se encarga de agregar al modelo de
             * manera automática el objeto user por lo que ya no es necesario agregarlo.
             * Esto es últil porque cuando regresemos de nueva cuenta a la página del
             * formulario ya tenemos el objeto original así como con los valores que fueron
             * introducidos en el formulario y ya no tenemos que cargarlos o escribirlos
             * nuevamente. También podemos omitir el envío del objeto error al modelo ya que
             * thymeleaft en conjunto con Spring se cordinan para el manejo de este objeto
             * en una página con thymeleaft.
             */
            return "form";
        }
        
        /*  Ya no es necesario envíar el usuario en el model porque este viaja a través de a session */
        // model.addAttribute("usuario", usuario);
        return "redirect:/ver";
    }
    
    @GetMapping("ver")
    public String ver(@SessionAttribute(name="user", required=false) Usuario usuario,  Model model, SessionStatus status){
        
        if (usuario == null) {
            return "redirect:/form";
        }

        model.addAttribute("titulo", "Parámetros recibidos del formulario");

        /*  Como ya existe el usuario en la sesión con el nombre user ya no sería necesario envíar nuevamente el objeto 
            usuario en el model.addAttribute() pero deberíamos cambiar en resultado.html el nombre de usuario por user, 
            de momento lo pasamos para no cambiar los nombres en el resultado.html
        */
        model.addAttribute("usuario", usuario);
        /*  Con la siguiente instrucción indicamos que @SessionAttributes("user") ha terminado y por lo 
            tanto los datos de la sesión ya no estarán disponibles */
        status.setComplete();
        return "resultado";
    }
}