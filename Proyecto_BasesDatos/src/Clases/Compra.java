
package Clases;

import java.util.ArrayList;

public class Compra {
    private String idCompra;
    private String idCliente;
    private String fecha;
    private String numProd;
    private Double total;

    public Compra(String idCompra, String idCliente, String fecha, String numProd, Double total) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.numProd = numProd;
        this.total = total;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumProd() {
        return numProd;
    }

    public void setNumProd(String numProd) {
        this.numProd = numProd;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
