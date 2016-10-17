package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by Pablo Paletto on 17/10/2016.
 */
public class DataHandler {
    private static  DataHandler handler;
    private List<Reserva> list;
    private boolean isResume;

    private DataHandler(){
    }

    public static DataHandler getInstance(){
        if (handler == null){
            handler = new DataHandler();
        }
        return handler;
    }

    public List<Reserva> getList(){
        return list;
    }

    public void setList(List<Reserva> list){
        this.list = list;
    }

    public boolean getIsResume(){
        return isResume;
    }

    public void setIsResume(boolean isResume){
        this.isResume = isResume;
    }
}
