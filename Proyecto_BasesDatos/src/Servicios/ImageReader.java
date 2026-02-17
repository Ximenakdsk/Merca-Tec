/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

/**
 *
 * @author Juan Pablo
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageReader {
    public byte[] leerImagen(File archivo) throws IOException {
        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] bytes = new byte[(int) archivo.length()];
            fis.read(bytes);
            return bytes;
        }
    }
}

