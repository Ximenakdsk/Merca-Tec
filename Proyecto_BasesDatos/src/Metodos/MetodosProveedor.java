/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;

import Clases.Proveedores.*;
import Servicios.ProveedoresService.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Juan Pablo
 */
public class MetodosProveedor {
    public static void registrarUnCliente(BufferedReader br) throws IOException, SQLException{
        System.out.println("Ingrese su nombre: ");
        String nombre = br.readLine();
        System.out.println("Ingrese apellido paterno: ");
        String apellidoPaterno = br.readLine();
        System.out.println("Ingrese apellido materno (Si no tiene, no ingrese nada): ");
        String apellidoMaterno = br.readLine();
        System.out.println("Ingrese su telefono: ");
        String telefono = br.readLine();
        System.out.println("Ingrese su email: ");
        String email = br.readLine();
        System.out.println("Ingrese una contraseña: ");
        String contrasena = br.readLine();
        System.out.println("Ingrese codigo postal: ");
        String codigoPostal = br.readLine();
        System.out.println("Calle: ");
        String calle = br.readLine();
        System.out.println("Numero: ");
        int num = Integer.parseInt(br.readLine());
        System.out.println("Donde vive (Clave): ");
        String cve = br.readLine();
//        
//        Clientes cliente = new Clientes("C00021", nombre, apellidoPaterno, apellidoMaterno, telefono, email, contrasena, codigoPostal, calle, num, cve);
//        ClientesService sv = new ClientesService();
//        sv.createAndUpdateClient(cliente);
    }
    
    public static void registrarUnProveedorGrafico(){
        
    }
}
