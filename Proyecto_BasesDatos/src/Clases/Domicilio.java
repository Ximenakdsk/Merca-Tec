
package Clases;

public class Domicilio {
    private String codigoDomicilio;
    private String ciudad;
    private String estado;
    private String pais;

    public Domicilio(String codigoDomicilio, String ciudad, String estado, String pais) {
        this.codigoDomicilio = codigoDomicilio;
        this.ciudad = ciudad;
        this.estado = estado;
        this.pais = pais;
    }

    public String getCodigoDomicilio() {
        return codigoDomicilio;
    }

    public void setCodigoDomicilio(String codigoDomicilio) {
        this.codigoDomicilio = codigoDomicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    @Override
    public String toString(){
        return "Cliente{co_Domicilio='"+ codigoDomicilio + "', ciudad='" + ciudad + "', codigo postal='" + estado + ", pais='" +  pais + "}";
    }
    
    
}
