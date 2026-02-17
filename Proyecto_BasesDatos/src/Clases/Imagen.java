/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Juan Pablo
 */
public class Imagen {
    private String idImagen;
    private String idProd;
    private byte[] datosImagen;

    public Imagen(String idImagen, String idProd, byte[] datosImagen) {
        this.idImagen = idImagen;
        this.idProd = idProd;
        this.datosImagen = datosImagen;
    }

    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public byte[] getDatosImagen() {
        return datosImagen;
    }

    public void setDatosImagen(byte[] datosImagen) {
        this.datosImagen = datosImagen;
    }
    
    
}
