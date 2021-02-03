package CONTROLLER;

import MODELO.MejorCorredor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class ControllerConsultas {



    public ControllerConsultas() {}

    public ArrayList<Integer> obtenerAnnosGiro(Connection connection){
        ArrayList<Integer> annos = new ArrayList<>();

        try{
            CallableStatement callableStatement = connection.prepareCall("EXEC SP_ObtenerAnnos ?");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
               annos.add(resultSet.getInt("Anno"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return annos;
    }

    public ArrayList<String> obtenerPaisesAnno(Connection connection, int anno){
        ArrayList<String> paises = new ArrayList<>();

        try{
            CallableStatement callableStatement = connection.prepareCall("EXEC SP_ObtenerNombresGiros ?, ?");
            callableStatement.setInt(1, anno);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                paises.add(resultSet.getString("nombreGiro"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return paises;
    }

    public ArrayList<MejorCorredor> mejoresCorredoresGiro(Connection connection, int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejoresGiro = new ArrayList<>();

        try{
            CallableStatement callableStatement = connection.prepareCall("EXEC SP_MejoresCorredoresGiro ?, ?, ?");
            callableStatement.setInt(1, anno);
            callableStatement.setString(2, nombreGiro);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                MejorCorredor corredor = new MejorCorredor(resultSet.getInt("PosicionFinal"), resultSet.getString("Nombre"), resultSet.getInt("tiempoAcumulado")); //cambiar el último a int
                mejoresGiro.add(corredor);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return mejoresGiro;
    }

    public ArrayList<MejorCorredor> corredoresMasRegulares(Connection connection, int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejoresGiro = new ArrayList<>();

        try{
            CallableStatement callableStatement = connection.prepareCall("EXEC SP_CorredoresMasRegulares ?, ?, ?");
            callableStatement.setInt(1, anno);
            callableStatement.setString(2, nombreGiro);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                MejorCorredor corredor = new MejorCorredor(resultSet.getInt("PosicionFinal"), resultSet.getString("Nombre"), resultSet.getInt("CantidadPuntos")); //cambiar el último a int
                mejoresGiro.add(corredor);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return mejoresGiro;
    }

    public ArrayList<MejorCorredor> mejoresCorredoresMontana(Connection connection, int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejoresGiro = new ArrayList<>();

        try{
            CallableStatement callableStatement = connection.prepareCall("EXEC SP_MejoresPuntuacionesMontana ?, ?, ?");
            callableStatement.setInt(1, anno);
            callableStatement.setString(2, nombreGiro);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                MejorCorredor corredor = new MejorCorredor(resultSet.getInt("PosicionFinal"), resultSet.getString("Nombre"), resultSet.getInt("CantidadPuntos")); //cambiar el último a int
                mejoresGiro.add(corredor);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return mejoresGiro;
    }

    public ArrayList<MejorCorredor> mejoresEquipos(Connection connection, int anno, String nombreGiro){
        ArrayList<MejorCorredor> mejoresGiro = new ArrayList<>();

        try{
            CallableStatement callableStatement = connection.prepareCall("EXEC SP_IGxEQ_EquiposGanadores ?, ?, ?");
            callableStatement.setInt(1, anno);
            callableStatement.setString(2, nombreGiro);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                MejorCorredor corredor = new MejorCorredor(resultSet.getInt("ID"), resultSet.getString("NombreEquipo"), resultSet.getInt("Tiempo")); //cambiar el último a int
                mejoresGiro.add(corredor);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return mejoresGiro;
    }
}
