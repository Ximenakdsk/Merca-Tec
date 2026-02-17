
package Clases;

import java.util.ArrayList;

public class Proveedores extends Persona{
    private String RFC;
    private ArrayList<Productos> listaProductos;

    public Proveedores(String id, String nombre, String apellidoP, String apellidoM, String telefono, String email, String contraseña, String codigoPostal, String coDomicilio, String rfc) {
        super(id, nombre, apellidoP, apellidoM, telefono, email, contraseña, codigoPostal, coDomicilio);
        this.RFC = rfc;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCoDomicilio() {
        return coDomicilio;
    }

    public void setCoDomicilio(String coDomicilio) {
        this.coDomicilio = coDomicilio;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    @Override
    public String toString(){
        return "Cliente{id='"+ id + "', rfc='" + RFC + "', codigo postal='" + codigoPostal + ", nombre='" +  nombre + "', apellido paterno='" + apellidoP + "', apellido materno='" + apellidoM + "', telefono='" + telefono + "', email='" + email + "', contraseña='" + contraseña  + "', clave domicilio='" + coDomicilio + "}";
    }
    
}
