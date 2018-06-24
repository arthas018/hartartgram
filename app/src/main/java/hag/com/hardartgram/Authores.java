package hag.com.hardartgram;

import java.util.ArrayList;

public class Authores {
    String nombre;
    String fecha;
    String description;
    ArrayList<String> imagen;
    String edad;
    String ciudad;
    String fotodeperfil;



  /**  public Authores(String nombre, String fecha, String description, ArrayList<String> imagen, String edad, String ciudad, String fotodeperfil) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.description = description;
        this.imagen = imagen;
        this.edad = edad;
        this.ciudad = ciudad;
        this.fotodeperfil = fotodeperfil;

    }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImagen() {
        return imagen;
    }

    public void setImagen(ArrayList<String> imagen) {
        this.imagen = imagen;
    }
     public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFotodeperfil() {
        return fotodeperfil;
    }

    public void setFotodeperfil(String fotodeperfil) {
        this.fotodeperfil = fotodeperfil;
    }
}
