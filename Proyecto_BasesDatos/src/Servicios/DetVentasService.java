
package Servicios;

import Clases.DetVentas;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class DetVentasService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpdateDetVentas (DetVentas det) throws SQLException{
        String query = "INSERT INTO DETVENTAS (ID_COMPRA, ID_PROD) " +
               "VALUES (?, ?)";
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, det.getIdCompra());
            statement.setString(2, det.getIdProd());
            statement.executeUpdate();
            System.out.println("Registro exitoso");
        }catch(SQLException e){
            System.out.println("Error al registrar " + e.getMessage());
        }
    }
    
    public DetVentas readDetVentasPorId(String idCompra) throws SQLException{
        DetVentas det = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM DETVENTAS WHERE ID_COMPRA = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idCompra);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        det = new DetVentas(resultSet.getString("ID_COMPRA"),
                                resultSet.getString("ID_PROD")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("VENTA NO ENCONTRADA " + e.getMessage());
        }
        return det;
    }
    
    public ArrayList<DetVentas> readAllDetVentas(String idComp) throws SQLException{
        ArrayList<DetVentas> detVent = new ArrayList<>();
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM DETVENTAS WHERE ID_COMPRA = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idComp);
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        DetVentas detV = new DetVentas(resultSet.getString("ID_COMPRA"),
                                resultSet.getString("ID_PROD")
                        );
                        detVent.add(detV);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron ventas " + e.getMessage());
        }
        return detVent;
    }
    
    public void deleteDetVentas(String idCompra) throws SQLException{
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "DELETE FROM DETVENTAS WHERE ID_COMPRA = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idCompra);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar la compra "+ e.getMessage());
        }
    }
}
