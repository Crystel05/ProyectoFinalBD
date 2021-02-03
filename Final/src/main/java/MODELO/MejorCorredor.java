package MODELO;

public class MejorCorredor {

    private int posicionFinal;
    private String nombre;
    private int cantPuntos;
    private String tiempo;

    public MejorCorredor(int posicionFinal, String nombre, int cantPuntos) {
        this.posicionFinal = posicionFinal;
        this.nombre = nombre;
        this.cantPuntos = cantPuntos;
    }

    public MejorCorredor(int posicionFinal, String nombre, String tiempo) {
        this.posicionFinal = posicionFinal;
        this.nombre = nombre;
        this.cantPuntos = cantPuntos;
        this.tiempo = tiempo;
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

    public void imprimir(){
        System.out.println(getNombre() + " " + getPosicionFinal() + " " + getCantPuntos());
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
