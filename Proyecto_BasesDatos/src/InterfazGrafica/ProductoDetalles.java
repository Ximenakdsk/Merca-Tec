/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterfazGrafica;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Juan Pablo
 */
public class ProductoDetalles extends JFrame{
    private JLabel lblIdProd; 
    private JLabel lblIdProv; 
    private JLabel lblNombre; 
    private JLabel lblDescripcion; 
    private JLabel lblPrecio; 
    private JLabel lblImagen;
    
    public ProductoDetalles(String idProd, String idProv, String nombre, String descripcion, double precio, ImageIcon imagen){
        setTitle("Detalles del Producto");
        setSize(1000, 1000);
        setLayout(new GridLayout(6,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel detailsPanel = new JPanel(); 
        detailsPanel.setLayout(new GridLayout(5, 1));
        
        lblIdProd = new JLabel("ID Producto: " + idProd); 
        lblIdProv = new JLabel("ID Proveedor: " + idProv); 
        lblNombre = new JLabel("Nombre: " + nombre); 
        lblDescripcion = new JLabel("Descripción: " + descripcion); 
        lblPrecio = new JLabel("Precio: " + precio); 
        
        detailsPanel.add(lblIdProd); 
        detailsPanel.add(lblIdProv); 
        detailsPanel.add(lblNombre); 
        detailsPanel.add(lblDescripcion); 
        detailsPanel.add(lblPrecio);
        
        
        lblImagen = new JLabel(imagen); 
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        lblImagen.setPreferredSize(new Dimension(1000, 1000));
        
        JScrollPane scrollPane = new JScrollPane(lblImagen); 
        scrollPane.setPreferredSize(new Dimension(1000, 1000)); // Ajustar el tamaño del JScrollPa
        add(detailsPanel, BorderLayout.NORTH); 
        add(scrollPane, BorderLayout.CENTER);
    }
    
//    public static void main(String[] args) { // Crear un JFrame de prueba ImageIcon testImage = new ImageIcon("path_to_image.jpg"); // Asegúrate de poner la ruta correcta de la imagen 
//        ProductoDetalles frame = new ProductoDetalles("1", "123", "Producto Ejemplo", "Descripción del Producto", 9.99, testImage); 
//        frame.setVisible(true);
//    }
}
