
package Clases;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Productos {
    private String idProd;
    private String noCateg;
    private String idProv;
    private String nombre;
    private String descripcion;
    private double precio;
    private int calif;
    private ArrayList<ImageIcon> imagenes;

    public Productos(String idProd, String noCateg, String idProv, String nombre, String descripcion, double precio, int calif) {
        this.idProd = idProd;
        this.noCateg = noCateg;
        this.idProv = idProv;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calif = calif;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public String getNoCateg() {
        return noCateg;
    }

    public void setNoCateg(String noCateg) {
        this.noCateg = noCateg;
    }

    public String getIdProv() {
        return idProv;
    }

    public void setIdProv(String idProv) {
        this.idProv = idProv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCalif() {
        return calif;
    }

    public void setCalif(int calif) {
        this.calif = calif;
    }

    public ArrayList<ImageIcon> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<ImageIcon> imagenes) {
        this.imagenes = imagenes;
    }
    
    
}
