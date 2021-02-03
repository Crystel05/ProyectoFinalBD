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
        ArrayList<MejorCorredor> mejoresTiempo = new ArrayList<>();

        for(MejorCorredor corredor: mejores){
            System.out.println(corredor.getCantPuntos());
            float segundos = (float)corredor.getCantPuntos()/60;
            float hora = segundos / 60;
            float minuto = (hora % 1) * 60;
            float segundo = (minuto%1)*60;
            String tiempo = (int)hora + " horas " + (int) minuto + " minutos " + (int)segundo + " segundos";
            MejorCorredor corredor1 = new MejorCorredor(corredor.getPosicionFinal(), corredor.getNombre(), tiempo);
            mejoresTiempo.add(corredor1);
        }
        return mejoresTiempo;
    }

    public ArrayList<MejorCorredor> corredoresMasRegulares(int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejores = controller.corredoresMasRegulares(connection, anno,nombreGiro);
        return mejores;
    }

    public ArrayList<MejorCorredor> mejoresCorredoresMontana(int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejores = controller.mejoresCorredoresMontana(connection, anno,nombreGiro);
        return mejores;
    }

    public ArrayList<MejorCorredor> mejoresEquipos(int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejores = controller.mejoresEquipos(connection, anno,nombreGiro);
        ArrayList<MejorCorredor> mejoresTiempo = new ArrayList<>();

        for(MejorCorredor corredor: mejores){
            float segundos = (float)corredor.getCantPuntos()/60;
            float hora = segundos / 60;
            float minuto = (hora % 1) * 60;
            float segundo = (minuto%1)*60;
            String tiempo = (int)hora + " horas " + (int) minuto + " min " + (int)segundo + " seg";
            MejorCorredor corredor1 = new MejorCorredor(corredor.getPosicionFinal(), corredor.getNombre(), tiempo);
            mejoresTiempo.add(corredor1);
        }
        return mejoresTiempo;
    }

}
