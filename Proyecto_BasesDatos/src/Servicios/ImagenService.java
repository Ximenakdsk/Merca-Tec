/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Clases.Imagen;
import Clases.Productos;
import conexiones.ConexionOracle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import java.sql.Blob;
import javax.swing.*; 
import java.awt.*; 
import java.awt.image.BufferedImage;

public class ImagenService {
    
    ConexionOracle con= new ConexionOracle();
    public void createAndUpdateImagen (Imagen img, ArrayList<Productos> listaProductos, String idProd) throws SQLException{
        Productos prod = null;
        String query = "INSERT INTO IMAGEN (ID_IMAG, ID_PROD, DATOS_IMAGEN) VALUES (?, ?, ?)";
        ProductosService PS = new ProductosService();
        try(Connection conexion = con.conectar()){
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, img.getIdImagen());
            statement.setString(2, img.getIdProd());
            ByteArrayInputStream blob = new ByteArrayInputStream(img.getDatosImagen());
            statement.setBlob(3, blob, img.getDatosImagen().length);
            statement.executeUpdate();
            ImageIcon imagen = transformarImagen(img.getDatosImagen());
            for (Productos p : listaProductos) {
                if (p.getIdProd().equals(idProd)) {
                    prod = p;
                    System.out.println("Producto: " + prod.getNombre());
                    prod.getImagenes().add(imagen);
                }
            }
            System.out.println("Registro de imagen exitoso");
        }catch(SQLException e){
            System.out.println("Error al registrar imagen " + e.getMessage());
        }
    }
    
    public void deleteImagen(String idImagen) throws SQLException{
        try(Connection conexion = con.conectar()){
            String query = "DELETE FROM IMAGEN WHERE ID_IMAG = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idImagen);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("No se pudo eliminar la imagen "+ e.getMessage());
        }
    }
    
    public ImageIcon readImagenesPorId(String idProd) throws SQLException, IOException {
        Imagen img = null;
        ImageIcon imgI = null;
        ImageIcon imgF = null;

        try (Connection conexion = con.conectar()) {
            String query = "SELECT * FROM IMAGEN WHERE ID_PROD = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, idProd);
                try (ResultSet resultSet = statement.executeQuery()) {
                    ArrayList<ImageIcon> IconosImagen = new ArrayList<>(); // Inicializar la lista

                    while (resultSet.next()) {
                        String idI = resultSet.getString("ID_IMAG");
                        String idP = resultSet.getString("ID_PROD");
                        InputStream blobInputStream = resultSet.getBlob("DATOS_IMAGEN").getBinaryStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = blobInputStream.read(buffer)) != -1) {
                            byteArrayOutputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] datosImagen = byteArrayOutputStream.toByteArray();
                        img = new Imagen(idI, idP, datosImagen);
                        System.out.println("img: " + img.getIdImagen());
                        System.out.println("Producto" + img.getIdProd());
                        imgI = transformarImagen(img.getDatosImagen());
                        if (imgI != null) {
                            IconosImagen.add(imgI);
                            if (IconosImagen.isEmpty()) {
                                System.out.println("No se agregó al arreglo");
                            } else {
                                System.out.println("Imagen agregada exitosamente");
                            }
                        } else {
                            System.out.println("Imagen nula");
                        }
                    }

                    imgF = combineImages(IconosImagen);
                    System.out.println(imgF);
                    return imgF;
                }
            }
        } catch (SQLException e) {
            System.out.println("IMAGEN NO ENCONTRADA " + e.getMessage());
        }
        return imgF;
    }
    
    public List<Imagen> readAllImagenes(String idProd) throws SQLException, IOException{
        List<Imagen> imagenes = new ArrayList<>();
        try(Connection conexion = con.conectar()){
            String query = "SELECT * FROM IMAGEN WHERE ID_PROD = ?";
            try(PreparedStatement statement = conexion.prepareStatement(query)){
                statement.setString(1, idProd);
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        String idI = resultSet.getString("ID_IMAG");
                        String idP = resultSet.getString("ID_PROD");
                        InputStream blobInputStream = resultSet.getBlob("DATOS_IMAGEN").getBinaryStream(); 
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 
                        byte[] buffer = new byte[4096]; 
                        int bytesRead = -1; 
                        while ((bytesRead = blobInputStream.read(buffer)) != -1) { 
                            byteArrayOutputStream.write(buffer, 0, bytesRead); 
                        } 
                        byte[] datosImagen = byteArrayOutputStream.toByteArray();
                        Imagen img = new Imagen(idI, idP, datosImagen);
                        imagenes.add(img);
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("No se encontraron imagenes " + e.getMessage());
        }
        return imagenes;
    }
    
    public ArrayList<String> getAllImagenIDs() throws SQLException {
        ArrayList<String> imagenesIDs = new ArrayList<>();
        String query = "SELECT ID_IMAG FROM IMAGEN";

        try (Connection conexion = con.conectar1();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                imagenesIDs.add(resultSet.getString("ID_IMAG"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los IDs de imagenes: " + e.getMessage());
            e.printStackTrace();
        }
        return imagenesIDs;
    }
    
    public ImageIcon transformarImagen(byte[] imageBytes) throws SQLException{
        if (imageBytes == null || imageBytes.length == 0) {
            return null;
        }
        return new ImageIcon(imageBytes);
    }
    
    public ImageIcon cargarImagen(String idProd) throws SQLException, IOException {
        ImageIcon imagenFinal = readImagenesPorId(idProd);
    return imagenFinal;
}
    
    public static ImageIcon combineImages(ArrayList<ImageIcon> images) {
        if (images.isEmpty()) {
            return null; // Retornar null si no hay imágenes
        }

        int width = 0;
        int height = 0;

        // Calcular el ancho máximo y la altura total de la imagen combinada
        for (ImageIcon icon : images) {
            width = Math.max(width, icon.getIconWidth());
            height += icon.getIconHeight();
        }

        // Crear un BufferedImage con las dimensiones calculadas
        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();

        // Dibujar cada imagen en el BufferedImage
        int currentHeight = 0;
        for (ImageIcon icon : images) {
            g.drawImage(icon.getImage(), 0, currentHeight, null);
            currentHeight += icon.getIconHeight();
        }

        g.dispose();
        return new ImageIcon(combined);
    }
}



