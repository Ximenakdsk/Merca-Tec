/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Clases.Productos;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.ArrayList;
import java.sql.DriverManager;
import java.util.List;

/**
 *
 * @author Juan Pablo
 */
public class ProductosService {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
    String usuario = "SYSTEM"; // Cambiar según tu usuario
    String contraseña = "oracle"; // Cambiar según tu contraseña
    ConexionOracle con = new ConexionOracle();
    //Primero hacer los metodos del modelo CRUD (Create, Read, Update, Delete)
    
    public void createAndUpdateProducto(Productos prod) throws SQLException {
    String query = "MERGE INTO PRODUCTOS T " +
                   "USING (SELECT ? AS ID_PROD, ? AS NO_CATEG, ? AS ID_PROVEEDORES, ? AS NOMBRE, ? AS DESCRIPCION, ? AS PRECIO, ? AS CALIFICACION FROM DUAL) S " +
                   "ON (T.ID_PROD = S.ID_PROD) " +
                   "WHEN MATCHED THEN " +
                   "UPDATE SET T.NO_CATEG = S.NO_CATEG, T.ID_PROVEEDORES = S.ID_PROVEEDORES, T.NOMBRE = S.NOMBRE, T.DESCRIPCION = S.DESCRIPCION, T.PRECIO = S.PRECIO, T.CALIFICACION = S.CALIFICACION " +
                   "WHEN NOT MATCHED THEN " +
                   "INSERT (ID_PROD, NO_CATEG, ID_PROVEEDORES, NOMBRE, DESCRIPCION, PRECIO, CALIFICACION) " +
                   "VALUES (S.ID_PROD, S.NO_CATEG, S.ID_PROVEEDORES, S.NOMBRE, S.DESCRIPCION, S.PRECIO, S.CALIFICACION)";
    try (Connection conexion = con.conectar1();
         PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
        preparedStatement.setString(1, prod.getIdProd());
        preparedStatement.setString(2, prod.getNoCateg());
        preparedStatement.setString(3, prod.getIdProv());
        preparedStatement.setString(4, prod.getNombre());
        preparedStatement.setString(5, prod.getDescripcion());
        preparedStatement.setDouble(6, prod.getPrecio());
        preparedStatement.setInt(7, prod.getCalif());
        preparedStatement.executeUpdate();
        System.out.println("Producto registrado o actualizado exitosamente");
    } catch (SQLException e) {
        System.out.println("Error al registrar o actualizar producto: " + e.getMessage());
    }
}

    
    public Productos readProductoPorId(String idProd) throws SQLException {
        Productos pr = null;
        String query = "SELECT * FROM PRODUCTOS WHERE ID_PROD = ?";
        try (Connection conexion = con.conectar1()) {
            System.out.println("Conexión establecida exitosamente");
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, idProd);
                System.out.println("Ejecutando consulta con ID_PROD: " + idProd);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Producto encontrado en la base de datos para ID_PROD: " + idProd);

                        pr = new Productos(resultSet.getString("ID_PROD"),
                                           resultSet.getString("NO_CATEG"),
                                           resultSet.getString("ID_PROVEEDORES"),
                                           resultSet.getString("NOMBRE"),
                                           resultSet.getString("DESCRIPCION"),
                                           resultSet.getDouble("PRECIO"),
                                           resultSet.getInt("CALIFICACION"));
                    } else {
                        System.out.println("No se encontró producto con ID_PROD: " + idProd);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("PRODUCTO NO ENCONTRADO " + e.getMessage());
            throw e; // Re-lanzar la excepción para que el llamador pueda manejarla
        }
        return pr;
    }
    
    public Productos readProductoPorNombre(String nombre) throws SQLException {
        Productos pr = null;
        String query = "SELECT * FROM PRODUCTOS WHERE NOMBRE = ?";
        try (Connection conexion = con.conectar1()) {
            System.out.println("Conexión establecida exitosamente");
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, nombre);
                System.out.println("Ejecutando consulta con NOMBRE: " + nombre);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Producto encontrado en la base de datos para NOMBRE: " + nombre);

                        pr = new Productos(resultSet.getString("ID_PROD"),
                                           resultSet.getString("NO_CATEG"),
                                           resultSet.getString("ID_PROVEEDORES"),
                                           resultSet.getString("NOMBRE"),
                                           resultSet.getString("DESCRIPCION"),
                                           resultSet.getDouble("PRECIO"),
                                           resultSet.getInt("CALIFICACION"));
                    } else {
                        System.out.println("No se encontró producto con NOMBRE: " + nombre);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("PRODUCTO NO ENCONTRADO " + e.getMessage());
            throw e; // Re-lanzar la excepción para que el llamador pueda manejarla
        }
        return pr;
    }
    
    public ArrayList<Productos> readAllProducts() throws SQLException{
        ArrayList<Productos> productos = new ArrayList<>();
        try(Connection conexion = con.conectar1()){
            String query = "SELECT * FROM PRODUCTOS";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Productos pr = new Productos(resultSet.getString("ID_PROD"),
                                resultSet.getString("NO_CATEG"),
                                resultSet.getString("ID_PROVEEDORES"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getDouble("PRECIO"),
                                resultSet.getInt("CALIFICACION")
                        );
                        productos.add(pr);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron productos " + e.getMessage());
        }
        return productos;
    }
    
    public ArrayList<Productos> readAllProductosProv() {
//        System.out.println(idProv);
    ArrayList<Productos> productos = new ArrayList<>();
    try (Connection conexion = con.conectar1()) {
        String query = "SELECT * FROM PRODUCTOS WHERE ID_PROVEEDORES = P15";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
//            statement.setString(1, idProv);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                // Instrucciones de depuración
                System.out.println("Consulta ejecutada, procesando ResultSet...");
                
                while (resultSet.next()) {
                    // Instrucciones de depuración
                    System.out.println("Producto encontrado en la base de datos");
                    
                    double precio = resultSet.getDouble("PRECIO");
                    Productos pr = new Productos(
                            resultSet.getString("ID_PROD"),
                            resultSet.getString("NO_CATEG"),
                            resultSet.getString("ID_PROVEEDORES"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("DESCRIPCION"),
                            precio,
                            resultSet.getInt("CALIFICACION")
                    );
                    productos.add(pr);
                    
                    // Instrucciones de depuración
                    System.out.println("Producto añadido a la lista: " + pr.getIdProd());
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("No se encontraron productos: " + e.getMessage());
    }
    // Depuración para verificar la cantidad de productos obtenidos
    System.out.println("Total de productos obtenidos: " + productos.size());
    return productos;
}
    
    public ArrayList<Productos> readAllProductosCateg(String noCateg) {
//        System.out.println(idProv);
    ArrayList<Productos> productos = new ArrayList<>();
        try(Connection conexion = con.conectar1()){
            String query = "SELECT * FROM PRODUCTOS WHERE NO_CATEG = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, noCateg);
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Productos pr = new Productos(resultSet.getString("ID_PROD"),
                                resultSet.getString("NO_CATEG"),
                                resultSet.getString("ID_PROVEEDORES"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getDouble("PRECIO"),
                                resultSet.getInt("CALIFICACION")
                        );
                        productos.add(pr);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron productos " + e.getMessage());
        }
        return productos;
}


    
    public void deleteProduct(String idProd) throws SQLException{
        try(Connection conexion = con.conectar1()){
            String query = "DELETE FROM PRODUCTOS WHERE ID_PROD = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idProd);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar el producto "+ e.getMessage());
        }
    }
    
    
    public ArrayList<String> getAllProductosIDs() throws SQLException {
        ArrayList<String> productosIDs = new ArrayList<>();
        String query = "SELECT ID_PROD FROM PRODUCTOS";

        try (Connection conexion = con.conectar1();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                productosIDs.add(resultSet.getString("ID_PROD"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los IDs de productos: " + e.getMessage());
            e.printStackTrace();
        }
        return productosIDs;
    }
}
