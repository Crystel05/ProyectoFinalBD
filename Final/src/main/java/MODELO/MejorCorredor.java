package MODELO;

public class MejorCorredor {

    private int posicionFinal;
    private String nombre;
    private float tiempoAcumulado;
    private int cantPuntos;

    public MejorCorredor(int posicionFinal, String nombre, float tiempoAcumulado) {
        this.posicionFinal = posicionFinal;
        this.nombre = nombre;
        this.tiempoAcumulado = tiempoAcumulado;
    }

    public MejorCorredor(int posicionFinal, String nombre, int cantPuntos) {
        this.posicionFinal = posicionFinal;
        this.nombre = nombre;
        this.cantPuntos = cantPuntos;
    }

    public int getCantPuntos() {
        return cantPuntos;
    }

    public void setCantPuntos(int cantPuntos) {
        this.cantPuntos = cantPuntos;
    }

    public int getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTiempoAcumulado() {
        return tiempoAcumulado;
    }

    public void setTiempoAcumulado(float tiempoAcumulado) {
        this.tiempoAcumulado = tiempoAcumulado;
    }
}
