/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Juan Pablo
 */
public class Carrito {
    private ArrayList<Productos> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }
    
    public void agregarProducto(Productos producto){
        productos.add(producto);
    }
    
    public void eliminarProducto(Productos producto){
//        productos.remove(producto);
        productos.removeIf(p -> p.getIdProd().equals(producto.getIdProd()));
    }
    
    public void eliminarTodosProductos(){
        productos.clear();
    }
    
    public ArrayList<Productos> verProductos() { 
        return productos;
    }
    
    public double calcularTotal(){
        double total = 0;
        for (Productos p : productos) {
            total += p.getPrecio();
        }
        return total;
    }
    
    public int calcularCantProd(){
        int cant = 0;
        for (Productos p : productos) {
            cant++;
        }
        return cant;
    }
}
