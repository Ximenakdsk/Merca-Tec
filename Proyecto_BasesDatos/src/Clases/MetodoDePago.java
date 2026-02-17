
package Clases;

public class MetodoDePago {
    private String idCliente; //Llave foranea
    private String numTarjeta;
    private String fechaExpiracion;
    private String tipo;

    public MetodoDePago(String idCliente, String numTarjeta, String fechaExpiracion, String tipo) {
        this.idCliente = idCliente;
        this.numTarjeta = numTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
