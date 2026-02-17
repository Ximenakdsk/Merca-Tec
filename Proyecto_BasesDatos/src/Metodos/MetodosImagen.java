/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Juan Pablo
 */
public class MetodosImagen {
    //este metodo va a transformar un archivo a un arreglo de bytes
    public static byte[] leerImagen(File archivo) throws FileNotFoundException, IOException{ 
        try(FileInputStream fis = new FileInputStream(archivo)){
            byte[] bytes = new byte[(int) archivo.length()];
            fis.read(bytes);
            return bytes;
        }
    }
}
