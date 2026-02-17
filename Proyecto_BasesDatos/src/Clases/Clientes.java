/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Servicios.ClientesService;
import Servicios.ComprasService;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan Pablo
 */
public class Clientes extends Persona{
    private String calle;
    private int numero;
    private ArrayList<MetodoDePago> metodosDePago;
    private ArrayList<Compra> compras;
    private Carrito carrito;

    public Clientes(String id, String nombre, String apellidoP, String apellidoM, String telefono, String email, String contraseña, String codigoPostal, String Calle, int Numero, String coDomicilio) throws SQLException {
        super(id, nombre, apellidoP, apellidoM, telefono, email, contraseña, codigoPostal, coDomicilio );
        this.calle = Calle;
        this.numero = Numero;
        this.compras = inicializarCompras();
        this.carrito = new Carrito();
    }
    
    public ArrayList<Compra> inicializarCompras() throws SQLException{
        ComprasService CS = new ComprasService();
        ArrayList<Compra> compras = CS.readAllCompras(id);
        return compras;
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
    
    
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<MetodoDePago> getMetodosDePago() {
        return metodosDePago;
    }

    public void setMetodosDePago(ArrayList<MetodoDePago> metodosDePago) {
        this.metodosDePago = metodosDePago;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    public Carrito getCarrito() {
        return carrito;
    }
    
    
    @Override
    public String toString(){
        return "Cliente{id='"+ id + ", nombre='" +  nombre + "', apellido paterno='" + apellidoP + "', apellido materno='" + apellidoM + "', telefono='" + telefono + "', email='" + email + "', contraseña='" + contraseña + "', codigo postal='" + codigoPostal + "', calle='" + calle + "', numero='" + numero + "', clave domicilio='" + coDomicilio + "}";
    }
    
    
}
