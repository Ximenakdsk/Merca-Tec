/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import Clases.Envio;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class EnvioService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpadateEnvio (Envio env) throws SQLException{
        String query = "MERGE INTO ENVIO T " +
               "USING (SELECT ? AS ID_ENVIO, ? AS ID_PROVEEDORES, ? AS ID_COMPRA, ? AS FECHA_ENVIO, ? AS FECHA_ENTREGA FROM DUAL) S " +
               "ON (T.ID_ENVIO = S.ID_ENVIO) " +
               "WHEN MATCHED THEN " +
               "UPDATE SET T.ID_PROVEEDORES = S.ID_PROVEEDORES, T.ID_COMPRA = S.ID_COMPRA, " + 
               "T.FECHA_ENVIO = S.FECHA_ENVIO, T.FECHA_ENTREGA = S.FECHA_ENTREGA " +
               "WHEN NOT MATCHED THEN " +
               "INSERT (ID_ENVIO, ID_PROVEEDORES, ID_COMPRA, FECHA_ENVIO, FECHA_ENTREGA) " +
               "VALUES (S.ID_ENVIO, S.ID_PROVEEDORES, S.ID_COMPRA, S.FECHA_ENVIO, S.FECHA_ENTREGA)";

        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, env.getIdEnvio());
            statement.setString(2, env.getIdProveedor());
            statement.setString(3, env.getIdCompra());
            statement.setString(4, env.getFechaEnvio());
            statement.setString(5, env.getFechaEntrega());
            statement.executeUpdate();
            System.out.println("Envio registrado o actualizado exitosamente");
        }catch(SQLException e){
            System.out.println("Error al registrar o actualzar envio " + e.getMessage());
        }
    }
    
    public Envio readEnvioPorId(String idEnvio) throws SQLException{
        Envio env = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM ENVIO WHERE ID_ENVIO = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idEnvio);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        env = new Envio(resultSet.getString("ID_ENVIO"),
                                resultSet.getString("ID_PROVEEDORES"),
                                resultSet.getString("ID_COMPRA"),
                                resultSet.getString("FECHA_ENVIO"),
                                resultSet.getString("FECHA_ENTREGA")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("ENVIO NO ENCONTRADO " + e.getMessage());
        }
        return env;
    }
    
    public Envio readEnvioPorIdCompra(String idCompra) throws SQLException{
        Envio env = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM ENVIO WHERE ID_COMPRA = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idCompra);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        env = new Envio(resultSet.getString("ID_ENVIO"),
                                resultSet.getString("ID_PROVEEDORES"),
                                resultSet.getString("ID_COMPRA"),
                                resultSet.getString("FECHA_ENVIO"),
                                resultSet.getString("FECHA_ENTREGA")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("ENVIO NO ENCONTRADO " + e.getMessage());
        }
        return env;
    }
    
    public ArrayList<String> getAllEnviosIDs() throws SQLException {
        ConexionOracle con = new ConexionOracle();
        ArrayList<String> enviosIDs = new ArrayList<>();
        String query = "SELECT ID_ENVIO FROM ENVIO";

        try (Connection conexion = con.conectar1();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                enviosIDs.add(resultSet.getString("ID_ENVIO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los IDs de COMPRA: " + e.getMessage());
            e.printStackTrace();
        }
        return enviosIDs;
    }
    
    public List<Envio> readAllEnvios(String idComp) throws SQLException{
        List<Envio> envios = new ArrayList<>();
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM ENVIO";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Envio env = new Envio(resultSet.getString("ID_ENVIO"),
                                resultSet.getString("ID_PROVEEDORES"),
                                resultSet.getString("ID_COMPRA"),
                                resultSet.getString("FECHA_ENVIO"),
                                resultSet.getString("FECHA_ENTREGA")
                        );
                        envios.add(env);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron envios " + e.getMessage());
        }
        return envios;
    }
    
    public void deleteEnvio(String idEnvio) throws SQLException{
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "DELETE FROM ENVIO WHERE ID_ENVIO = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idEnvio);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar el envio "+ e.getMessage());
        }
    }
    
    
}
