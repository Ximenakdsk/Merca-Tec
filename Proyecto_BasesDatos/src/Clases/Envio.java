
package Clases;


public class Envio {
    private String idEnvio;
    private String idProveedor;
    private String idCompra;
    private String fechaEnvio;
    private String fechaEntrega;

    public Envio(String idEnvio, String idProveedor, String idCompra, String fechaEnvio, String fechaEntrega) {
        this.idEnvio = idEnvio;
        this.idProveedor = idProveedor;
        this.idCompra = idCompra;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
}
