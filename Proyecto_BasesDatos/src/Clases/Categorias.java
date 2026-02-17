/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;

public class Categorias {
    private String noCateg;
    private String nombre;
    private ArrayList<Productos> listaProductos;

    public Categorias(String noCateg, String nombre) {
        this.noCateg = noCateg;
        this.nombre = nombre;
    }

    public String getNoCateg() {
        return noCateg;
    }

    public void setNoCateg(String noCateg) {
        this.noCateg = noCateg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    
}
