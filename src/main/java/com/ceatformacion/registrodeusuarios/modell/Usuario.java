package com.ceatformacion.registrodeusuarios.modell;

import java.time.LocalDate;

public class Usuario {

    private String nombreApellidos;
    private String username;
    private String password;
    private String email;
    private static int id=0;
    private int idUsuario=0;
    private LocalDate fechaRegistro;

    public Usuario() {}

    public Usuario(int idAlumno, String nombreApellidos, String username, String password, String email, String fechaRegistro) {
        this.idUsuario = idAlumno;
        this.nombreApellidos = nombreApellidos;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fechaRegistro = LocalDate.parse(fechaRegistro);
    }

    //metodos getter y setter
    //metodo temporal porque cuando usemos BBDD este id se autogenera
    public void asignarIdUsuario() {
        this.idUsuario = ++id;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }



    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

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

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getFechaRegistro() {
        return String.valueOf(this.fechaRegistro);
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = LocalDate.parse(fechaRegistro);
    }

    @Override
    public String toString() {
        return "Usuario-> " +
                "\nNombreApellidos: " + nombreApellidos +
                "\nUsername: " + username +
                "\nEmail: " + email +
                "\nIdUsuario: " + idUsuario +
                "\nFechaRegistro: " + fechaRegistro;
    }


}
