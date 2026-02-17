/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import Clases.Categorias;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

public class CategoriasService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpadateCategoria (Categorias cat) throws SQLException{
        String query = "MERGE INTO CATEGORIAS T" +
                        "USING (SELECT ? AS NO_CATEG, ? AS NOMBRE FROM DUAL) S"+
                        "ON (T.NO_CATEG = S.NO_CATEG) " +
                        "WHEN MATCHED THEN " +
                        "UPDATE SET T.NO_CATEG = S.NO_CATEG, T.NOMBRE = S.NOMBRE" + 
                        "WHEN NOT MATCHED THEN " +
                        "INSERT (NO_CATEG, NOMBRE) " +
                        "VALUES(S.NO_CATEG, S.NOMBRE)";
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, cat.getNoCateg());
            statement.setString(2, cat.getNombre());
            statement.executeUpdate();
            System.out.println("Categoria registrada o actualizada exitosamente");
        }catch(SQLException e){
            System.out.println("Error al registrar o actualizar categoria " + e.getMessage());
        }
    }
    
    public Categorias readCategoriaPorId(String noCateg) throws SQLException{
        Categorias ct = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM CATEGORIAS WHERE NO_CATEG = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, noCateg);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        ct = new Categorias(resultSet.getString("NO_CATEG"),
                                resultSet.getString("NOMBRE")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("CATEGORIA NO ENCONTRADA " + e.getMessage());
        }
        return ct;
    }
    
    public Categorias readCategoriaPorNombre(String nombre){
        Categorias ct = null;
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM CATEGORIAS WHERE NOMBRE = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, nombre);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        ct = new Categorias(resultSet.getString("NO_CATEG"),
                                resultSet.getString("NOMBRE")
                        );
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("CATEGORIA NO ENCONTRADA " + e.getMessage());
        }
        return ct;
    }
    
    public List<Categorias> readAllCategorias(String idComp) throws SQLException{
        List<Categorias> categorias = new ArrayList<>();
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "SELECT * FROM CATEGORIAS";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Categorias ct = new Categorias(resultSet.getString("NO_CATEG"),
                                resultSet.getString("NOMBRE")
                        );
                        categorias.add(ct);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron categorias " + e.getMessage());
        }
        return categorias;
    }
    
    public void deleteCategoria(String noCateg) throws SQLException{
        try(Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)){
            String query = "DELETE FROM CATEGORIAS WHERE NO_CATEG = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, noCateg);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar la categoria "+ e.getMessage());
        }
    }
}
