
package Servicios;

import Clases.Domicilio;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class DomicilioService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpdateDomicilio(Domicilio domicilio) throws SQLException {
    String query = "MERGE INTO DOMICILIO T " +
                   "USING (SELECT ? AS CO_DOMICILIO, ? AS CIUDAD, ? AS ESTADO, ? AS PAIS FROM DUAL) S " +
                   "ON (T.CO_DOMICILIO = S.CO_DOMICILIO) " +
                   "WHEN MATCHED THEN " +
                   "UPDATE SET T.CIUDAD = S.CIUDAD, T.ESTADO = S.ESTADO, T.PAIS = S.PAIS " +
                   "WHEN NOT MATCHED THEN " +
                   "INSERT (CO_DOMICILIO, CIUDAD, ESTADO, PAIS) " +
                   "VALUES (S.CO_DOMICILIO, S.CIUDAD, S.ESTADO, S.PAIS)";
    try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
         PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
        preparedStatement.setString(1, domicilio.getCodigoDomicilio());
        preparedStatement.setString(2, domicilio.getCiudad());
        preparedStatement.setString(3, domicilio.getEstado());
        preparedStatement.setString(4, domicilio.getPais());
        preparedStatement.executeUpdate();
        System.out.println("Domicilio registrado o actualizado exitosamente");
    } catch (SQLException e) {
        System.out.println("Error al registrar o actualizar domicilio: " + e.getMessage());
    }
}
    
    public Domicilio readDomicilioByCodigo(String codigo) throws SQLException{
        Domicilio ciudad = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM DOMICILIO WHERE CO_DOMICILIO = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, codigo);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        ciudad = new Domicilio(resultSet.getString("CO_DOMICILIO"),
                                resultSet.getString("CIUDAD"),
                                resultSet.getString("ESTADO"),
                                resultSet.getString("PAIS")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("CIUDAD NO ENCONTRADA " + e.getMessage());
        }
        return ciudad;
    }
    
    public ArrayList<Domicilio> readAllCiudades() throws SQLException{
        ArrayList<Domicilio> ciudades = new ArrayList<>();
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM DOMICILIO";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Domicilio ciudad = new Domicilio(resultSet.getString("CO_DOMICILIO"),
                                resultSet.getString("CIUDAD"),
                                resultSet.getString("ESTADO"),
                                resultSet.getString("PAIS")
                        );
                        ciudades.add(ciudad);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron ciudades " + e.getMessage());
        }
        return ciudades;
    }
    
    
}
