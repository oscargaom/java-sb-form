package com.bolsadeideas.springboot.app.springbootform.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.bolsadeideas.springboot.app.springbootform.validation.IdentificadorRegex;
import com.bolsadeideas.springboot.app.springbootform.validation.Requerido;

public class Usuario {
    
    private Long identity;
    
    // @NotEmpty
    private String nombre;
    
    // @NotEmpty
    @Requerido
    private String apellido;
    
    @NotBlank
    @Size(min = 3, max = 8)
    private String username;
    
    @NotEmpty
    private String password;    
    
    @NotEmpty
    @Email(message = "El email no es un correo valido")
    private String email;

    // @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")
    @IdentificadorRegex
    private String matricula;
    
    @NotNull
    @Min(5)
    @Max(5000)
    private Integer cuenta;

    @NotNull
    @Past
    // @Future
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotEmpty
    private String pais;

    @NotEmpty
    private String paisMap;

    /*  La anotación @Valid permite que podamos realizar las validaciones de las anotaciones 
        definidas en el objeto Pais. De no hacerlo va a ignorar las anotaciones definidas en 
        dicha clase.*/
    // @Valid
    /*  Con la anotación @NotNull a nivel de clase válida que ninguno de los campos de la 
        del objeto que instancia la clase sea vacío
    */
    @NotNull
    private Pais paisObject;

    @NotEmpty
    private List<String> rolesStr;

    @NotEmpty
    private List<Role> rolesObj;

    private Boolean habilitar;

    @NotEmpty
    private String genero;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPaisMap() {
        return paisMap;
    }

    public void setPaisMap(String paisMap) {
        this.paisMap = paisMap;
    }

    public Pais getPaisObject() {
        return paisObject;
    }

    public void setPaisObject(Pais paisObject) {
        this.paisObject = paisObject;
    }

    public List<String> getRolesStr() {
        return rolesStr;
    }

    public void setRolesStr(List<String> rolesStr) {
        this.rolesStr = rolesStr;
    }

    public List<Role> getRolesObj() {
        return rolesObj;
    }

    public void setRolesObj(List<Role> rolesObj) {
        this.rolesObj = rolesObj;
    }

    public Boolean getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Boolean habilitar) {
        this.habilitar = habilitar;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
