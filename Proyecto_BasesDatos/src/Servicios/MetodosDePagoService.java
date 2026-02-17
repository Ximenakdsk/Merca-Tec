
package Servicios;

import Clases.MetodoDePago;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class MetodosDePagoService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpdateMetodoDePago(MetodoDePago mp) throws SQLException {
    String query = "MERGE INTO METODOPAGO T " +
                   "USING (SELECT ? AS ID_CTE, ? AS NUM_TARJETA, ? AS FECHA_EXPIRACION, ? AS TIPO FROM DUAL) S " +
                   "ON (T.NUM_TARJETA = S.NUM_TARJETA) " +
                   "WHEN MATCHED THEN " +
                   "UPDATE SET T.ID_CTE = S.ID_CTE, T.FECHA_EXPIRACION = S.FECHA_EXPIRACION, T.TIPO = S.TIPO " +
                   "WHEN NOT MATCHED THEN " +
                   "INSERT (ID_CTE, NUM_TARJETA, FECHA_EXPIRACION, TIPO) " +
                   "VALUES (S.ID_CTE, S.NUM_TARJETA, S.FECHA_EXPIRACION, S.TIPO)";
    try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
         PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
        preparedStatement.setString(1, mp.getIdCliente());
        preparedStatement.setString(2, mp.getNumTarjeta());
        preparedStatement.setString(3, mp.getFechaExpiracion());
        preparedStatement.setString(4, mp.getTipo());
        preparedStatement.executeUpdate();
        System.out.println("Método de pago registrado o actualizado exitosamente");
    } catch (SQLException e) {
        System.out.println("Error al registrar o actualizar método de pago: " + e.getMessage());
    }
}
    
    public MetodoDePago readMetodoPagoPorNumTarj(String numT) throws SQLException{
        MetodoDePago mp = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM METODOPAGO WHERE NUM_TARJETA = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, numT);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        mp = new MetodoDePago(resultSet.getString("ID_CTE"),
                                resultSet.getString("NUM_TARJETA"),
                                resultSet.getString("FECHA_EXPIRACION"),
                                resultSet.getString("TIPO")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("METODO DE PAGO NO ENCONTRADO " + e.getMessage());
        }
        return mp;
    }
    
    public List<MetodoDePago> readAllMp(String idCliente) throws SQLException{
        List<MetodoDePago> metodos = new ArrayList<>();
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM METODOPAGO WHERE ID_CTE = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idCliente);
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        MetodoDePago mp = new MetodoDePago(resultSet.getString("ID_CTE"),
                                resultSet.getString("NUM_TARJETA"),
                                resultSet.getString("FECHA_EXPIRACION"),
                                resultSet.getString("TIPO")
                        );
                        metodos.add(mp);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron metodos de pago del cliente " + e.getMessage());
        }
        return metodos;
    }
    
    public void deleteMetodoP(String id) throws SQLException{
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "DELETE FROM METODOPAGO WHERE ID_CTE = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar el metodo de pago "+ e.getMessage());
        }
    }
    
    
}
