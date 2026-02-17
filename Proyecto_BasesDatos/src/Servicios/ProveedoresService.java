/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import conexiones.ConexionOracle;
import Clases.Proveedores;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class ProveedoresService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    ConexionOracle con = new ConexionOracle();
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpdateProveedor(Proveedores proveedor) throws SQLException {
        ConexionOracle con = new ConexionOracle();
    String query = "MERGE INTO PROVEEDORES T " +
                   "USING (SELECT ? AS ID_PROVEEDORES, ? AS RFC, ? AS CODIGO_POSTAL, ? AS NOMBRE, ? AS APELLIDO_PATERNO, ? AS APELLIDO_MATERNO, ? AS TELEFONO, ? AS EMAIL, ? AS CONTRASEÑA, ? AS CO_DOMICILIO FROM DUAL) S " +
                   "ON (T.ID_PROVEEDORES = S.ID_PROVEEDORES) " +
                   "WHEN MATCHED THEN " +
                   "UPDATE SET T.RFC = S.RFC, T.CODIGO_POSTAL = S.CODIGO_POSTAL, T.NOMBRE = S.NOMBRE, T.APELLIDO_PATERNO = S.APELLIDO_PATERNO, T.APELLIDO_MATERNO = S.APELLIDO_MATERNO, T.TELEFONO = S.TELEFONO, T.EMAIL = S.EMAIL, T.CONTRASEÑA = S.CONTRASEÑA, T.CO_DOMICILIO = S.CO_DOMICILIO " +
                   "WHEN NOT MATCHED THEN " +
                   "INSERT (ID_PROVEEDORES, RFC, CODIGO_POSTAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, TELEFONO, EMAIL, CONTRASEÑA, CO_DOMICILIO) " +
                   "VALUES (S.ID_PROVEEDORES, S.RFC, S.CODIGO_POSTAL, S.NOMBRE, S.APELLIDO_PATERNO, S.APELLIDO_MATERNO, S.TELEFONO, S.EMAIL, S.CONTRASEÑA, S.CO_DOMICILIO)";
    try (Connection conexion = con.conectar1();
         PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
        preparedStatement.setString(1, proveedor.getId());
        preparedStatement.setString(2, proveedor.getRFC());
        preparedStatement.setString(3, proveedor.getCodigoPostal());
        preparedStatement.setString(4, proveedor.getNombre());
        preparedStatement.setString(5, proveedor.getApellidoP());
        preparedStatement.setString(6, proveedor.getApellidoM().isEmpty() ? null : proveedor.getApellidoM());
        preparedStatement.setString(7, proveedor.getTelefono());
        preparedStatement.setString(8, proveedor.getEmail());
        preparedStatement.setString(9, proveedor.getContraseña());
        preparedStatement.setString(10, proveedor.getCoDomicilio());
        preparedStatement.executeUpdate();
        System.out.println("Proveedor registrado o actualizado exitosamente");
    } catch (SQLException e) {
        System.out.println("Error al registrar o actualizar proveedor: " + e.getMessage());
    }
}

    
    public Proveedores readClientById(String id) throws SQLException{
        ConexionOracle con = new ConexionOracle();
        Proveedores proveedor = null;
        try(Connection conexion = con.conectar1()){
            String query = "SELECT * FROM PROVEEDORES WHERE ID_CTE = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, id);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        proveedor = new Proveedores(resultSet.getString("ID"),
                                resultSet.getString("RFC"),
                                resultSet.getString("CODIGO_POSTAL"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO_PATERNO"),
                                resultSet.getString("APELLIDO_MATERNO"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getString("EMAIL"),
                                resultSet.getString("CONTRASEÑA"),
                                resultSet.getString("CO_DOMICILIO")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("CLIENTE NO ENCONTRADO " + e.getMessage());
        }
        return proveedor;
    }
    
    public ArrayList<Proveedores> readAllProv() throws SQLException{
        ConexionOracle con = new ConexionOracle();
        ArrayList<Proveedores> proveedores = new ArrayList<>();
        try(Connection conexion = con.conectar1()){
            String query = "SELECT * FROM PROVEEDORES";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Proveedores proveedor = new Proveedores(resultSet.getString("ID"),
                                resultSet.getString("RFC"),
                                resultSet.getString("CODIGO POSTAL"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO PATERNO"),
                                resultSet.getString("APELLIDO MATERNO"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getString("EMAIL"),
                                resultSet.getString("CONTRASEÑA"),
                                resultSet.getString("CO_DOMICILIO")
                        );
                        proveedores.add(proveedor);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron proveedores " + e.getMessage());
        }
        return proveedores;
    }

    public ArrayList<String> getAllProveedoresIDs() throws SQLException {
        ArrayList<String> proveedoresIDs = new ArrayList<>();
        String query = "SELECT ID_PROVEEDORES FROM PROVEEDORES";

        try (Connection conexion = con.conectar1();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                proveedoresIDs.add(resultSet.getString("ID_PROVEEDORES"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los IDs de proveedores: " + e.getMessage());
            e.printStackTrace();
        }
        return proveedoresIDs;
    }
    

    
    public void deleteProveedor(String id) throws SQLException{
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "DELETE FROM PROVEEDORES WHERE ID_CTE = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar el proveedor "+ e.getMessage());
        }
    }
    
    //Iniciar sesion
//    public boolean validarCredenciales(String email, String contraseña) throws SQLException{
//        boolean valido = false;
//        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, jdbcURL)){
//            String query = "SELECT * FROM PROVEEDORES WHERE EMAIL = ? AND CONTRASEÑA = ?";
//            try (PreparedStatement statement = conexion.prepareStatement(query)){
//                statement.setString(1, email);
//                statement.setString(2, contraseña);
//                try(ResultSet resultSet = statement.executeQuery()){
//                    if (resultSet.next()) {
//                        valido = true;
//                    }
//                }
//            }
//        }catch (SQLException e){
//            System.out.println("No se pudo autenticar sus credenciales, verifique sus datos");
//        }
//        return valid
//    }
    
    
}
