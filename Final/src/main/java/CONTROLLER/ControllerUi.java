package CONTROLLER;

import MODELO.MejorCorredor;

import java.sql.Connection;
import java.util.ArrayList;

public class ControllerUi {

    private ControllerConsultas controller = new ControllerConsultas();
    private Connection connection = ControllerConexion.getInstance().connection;

    private int anno = 0;
    private String nombreGiro = null;

    public ControllerUi() {}

    private static ControllerUi controllerUI;

    public static ControllerUi getInstance(){
        if(controllerUI == null){
            controllerUI = new ControllerUi();
        }
        return controllerUI;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getNombreGiro() {
        return nombreGiro;
    }

    public void setNombreGiro(String nombreGiro) {
        this.nombreGiro = nombreGiro;
    }

    public ArrayList <Integer> mostrarAnnos(){
        ArrayList<Integer> annos = controller.obtenerAnnosGiro(connection);
        return annos;
    }

    public ArrayList <String> mostrarNombresGiro(int anno){
        ArrayList<String> paises = controller.obtenerPaisesAnno(connection, anno);
        return paises;
    }

    public ArrayList<MejorCorredor> mejoresGiro(int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejores = controller.mejoresCorredoresGiro(connection, anno,nombreGiro);
        return mejores;
    }

    public ArrayList<MejorCorredor> corredoresMasRegulares(int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejores = controller.corredoresMasRegulares(connection, anno,nombreGiro);
        return mejores;
    }
}
