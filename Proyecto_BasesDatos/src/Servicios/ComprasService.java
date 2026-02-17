
package Servicios;

import Clases.Compra;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class ComprasService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpdateCompra(Compra comp) throws SQLException {
        String query = "MERGE INTO COMPRA T " +
                       "USING (SELECT ? AS ID_COMPRA, ? AS ID_CTE, ? AS FECHA, ? AS NUM_PRODS, ? AS TOTAL FROM DUAL) S " +
                       "ON (T.ID_COMPRA = S.ID_COMPRA) " +
                       "WHEN MATCHED THEN " +
                       "UPDATE SET T.ID_CTE = S.ID_CTE, T.FECHA = S.FECHA, T.NUM_PRODS = S.NUM_PRODS, T.TOTAL = S.TOTAL " +
                       "WHEN NOT MATCHED THEN " +
                       "INSERT (ID_COMPRA, ID_CTE, FECHA, NUM_PRODS, TOTAL) " +
                       "VALUES (S.ID_COMPRA, S.ID_CTE, S.FECHA, S.NUM_PRODS, S.TOTAL)";
        try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
            PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
                preparedStatement.setString(1, comp.getIdCompra());
                preparedStatement.setString(2, comp.getIdCliente());
                preparedStatement.setString(3, comp.getFecha()); // Asegúrate de que el formato de fecha sea correcto
                preparedStatement.setString(4, comp.getNumProd());
                preparedStatement.setDouble(5, comp.getTotal());
                preparedStatement.executeUpdate();
                System.out.println("Compra registrada o actualizada exitosamente");
            } catch (SQLException e) {
                System.out.println("Error al registrar o actualizar compra: " + e.getMessage());
        }
    }

    
    public Compra readCompraPorId(String idComp) throws SQLException{
        Compra cp = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM COMPRA WHERE ID_COMPRA = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idComp);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        cp = new Compra(resultSet.getString("ID_COMPRA"),
                                resultSet.getString("ID_CTE"),
                                resultSet.getString("FECHA"),
                                resultSet.getString("NUM_PRODS"),
                                resultSet.getDouble("TOTAL")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("COMPRA NO ENCONTRADA " + e.getMessage());
        }
        return cp;
    }
    
    public ArrayList<Compra> readAllCompras(String idCte) throws SQLException{
        ArrayList<Compra> compras = new ArrayList<>();
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM COMPRA WHERE ID_CTE = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idCte);
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Compra cp = new Compra(resultSet.getString("ID_COMPRA"),
                                resultSet.getString("ID_CTE"),
                                resultSet.getString("FECHA"),
                                resultSet.getString("NUM_PRODS"),
                                resultSet.getDouble("TOTAL")
                        );
                        compras.add(cp);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron compras " + e.getMessage());
        }
        return compras;
    }
    
    public ArrayList<String> getAllComprasIDs() throws SQLException {
        ConexionOracle con = new ConexionOracle();
        ArrayList<String> comprasIDs = new ArrayList<>();
        String query = "SELECT ID_COMPRA FROM COMPRA";

        try (Connection conexion = con.conectar1();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                comprasIDs.add(resultSet.getString("ID_COMPRA"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los IDs de COMPRA: " + e.getMessage());
            e.printStackTrace();
        }
        return comprasIDs;
    }
    
    public void deleteCompra(String idComp) throws SQLException{
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "DELETE FROM COMPRA WHERE ID_COMPRA = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idComp);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar la compra "+ e.getMessage());
        }
    }
    
    
}
