/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import conexiones.ConexionOracle;
import Clases.Clientes;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class ClientesService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
        String usuario = "SYSTEM"; // Cambiar según tu usuario
        String contraseña = "oracle"; // Cambiar según tu contraseña
        ConexionOracle con = new ConexionOracle();
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
        
    public void createAndUpdateClient(Clientes cliente) throws SQLException {
    String query = "MERGE INTO CLIENTE T " +
                   "USING (SELECT ? AS ID_CTE, ? AS NOMBRE, ? AS APELLIDO_PATERNO, ? AS APELLIDO_MATERNO, ? AS TELEFONO, ? AS EMAIL, ? AS CONTRASEÑA, ? AS CODIGO_POSTAL, ? AS CALLE, ? AS NUMERO, ? AS CO_DOMICILIO FROM DUAL) S " +
                   "ON (T.ID_CTE = S.ID_CTE) " +
                   "WHEN MATCHED THEN " +
                   "UPDATE SET T.NOMBRE = S.NOMBRE, T.APELLIDO_PATERNO = S.APELLIDO_PATERNO, T.APELLIDO_MATERNO = S.APELLIDO_MATERNO, T.TELEFONO = S.TELEFONO, T.EMAIL = S.EMAIL, T.CONTRASEÑA = S.CONTRASEÑA, T.CODIGO_POSTAL = S.CODIGO_POSTAL, T.CALLE = S.CALLE, T.NUMERO = S.NUMERO, T.CO_DOMICILIO = S.CO_DOMICILIO " +
                   "WHEN NOT MATCHED THEN " +
                   "INSERT (ID_CTE, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, TELEFONO, EMAIL, CONTRASEÑA, CODIGO_POSTAL, CALLE, NUMERO, CO_DOMICILIO) " +
                   "VALUES (S.ID_CTE, S.NOMBRE, S.APELLIDO_PATERNO, S.APELLIDO_MATERNO, S.TELEFONO, S.EMAIL, S.CONTRASEÑA, S.CODIGO_POSTAL, S.CALLE, S.NUMERO, S.CO_DOMICILIO)";
    try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
         PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
        preparedStatement.setString(1, cliente.getId());
        preparedStatement.setString(2, cliente.getNombre());
        preparedStatement.setString(3, cliente.getApellidoP());
        preparedStatement.setString(4, cliente.getApellidoM().isEmpty() ? null : cliente.getApellidoM());
        preparedStatement.setString(5, cliente.getTelefono());
        preparedStatement.setString(6, cliente.getEmail());
        preparedStatement.setString(7, cliente.getContraseña());
        preparedStatement.setString(8, cliente.getCodigoPostal());
        preparedStatement.setString(9, cliente.getCalle());
        preparedStatement.setString(10, String.valueOf(cliente.getNumero()));
        preparedStatement.setString(11, cliente.getCoDomicilio());
        preparedStatement.executeUpdate();
        System.out.println("Cliente registrado o actualizado exitosamente");
    } catch (SQLException e) {
        System.out.println("Error al registrar o actualizar cliente: " + e.getMessage());
    }
    }
    
    public Clientes readClientById(String id) throws SQLException{
        Clientes cliente = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM CLIENTE WHERE ID_CTE = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, id);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        cliente = new Clientes(resultSet.getString("ID_CTE"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO_PATERNO"),
                                resultSet.getString("APELLIDO_MATERNO"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getString("EMAIL"),
                                resultSet.getString("CONTRASEÑA"),
                                resultSet.getString("CODIGO_POSTAL"),
                                resultSet.getString("CALLE"),
                                resultSet.getInt("NUMERO"),
                                resultSet.getString("CO_DOMICILIO")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("CLIENTE NO ENCONTRADO " + e.getMessage());
        }
        return cliente;
    }
    
    public List<Clientes> readAllClients() throws SQLException{
        List<Clientes> clientes = new ArrayList<>();
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM CLIENTE";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Clientes cliente = new Clientes(resultSet.getString("ID_CTE"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO_PATERNO"),
                                resultSet.getString("APELLIDO_MATERNO"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getString("EMAIL"),
                                resultSet.getString("CONTRASEÑA"),
                                resultSet.getString("CODIGO_POSTAL"),
                                resultSet.getString("CALLE"),
                                resultSet.getInt("NUMERO"),
                                resultSet.getString("CO_DOMICILIO")
                        );
                        clientes.add(cliente);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron clientes " + e.getMessage());
        }
        return clientes;
    }
    
    public void deleteClient(String id) throws SQLException{
        ConexionOracle con = new ConexionOracle();
        try(Connection conexion = con.conectar1()){
            String query = "DELETE FROM CLIENTE WHERE ID_CTE = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
                System.out.println("Cliente eliminado exitosamente");
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar el cliente "+ e.getMessage());
        }
    }
    
    public ArrayList<String> getAllClientesIDs() throws SQLException {
        ArrayList<String> clientesIDs = new ArrayList<>();
        String query = "SELECT ID_CTE FROM CLIENTE";

        try (Connection conexion = con.conectar1();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                clientesIDs.add(resultSet.getString("ID_CTE"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los IDs de CLIENTES: " + e.getMessage());
            e.printStackTrace();
        }
        return clientesIDs;
    }
    
    
    
    //Iniciar sesion
    public boolean validarCredenciales(String email, String contraseña) throws SQLException{
        ConexionOracle cn = new ConexionOracle();
        boolean valido = false;
        try(Connection conexion = cn.conectar1()){
            String query = "SELECT * FROM CLIENTE WHERE EMAIL = ? AND CONTRASEÑA = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, email);
                statement.setString(2, contraseña);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        valido = true;
                        return valido;
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("No se pudo autenticar sus credenciales, verifique sus datos");
        }
        return valido;
    }
    //METODO PARA OBTENER CLIENTE DESPUES DE VALIDAR SU INICIO DE SESION
    public Clientes obtenerClientePorEmail(String email) throws SQLException{
        ConexionOracle cn = new ConexionOracle();
        Clientes cliente = null;
        try(Connection conexion = cn.conectar1()){
            String query = "SELECT * FROM CLIENTE WHERE EMAIL = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, email);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        cliente = new Clientes(resultSet.getString("ID_CTE"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO_PATERNO"),
                                resultSet.getString("APELLIDO_MATERNO"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getString("EMAIL"),
                                resultSet.getString("CONTRASEÑA"),
                                resultSet.getString("CODIGO_POSTAL"),
                                resultSet.getString("CALLE"),
                                resultSet.getInt("NUMERO"),
                                resultSet.getString("CO_DOMICILIO"));
                        return cliente;
                    }
                }
            }
        }
        return cliente;
    }
    
}
