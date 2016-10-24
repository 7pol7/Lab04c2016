package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by Pablo Paletto on 24/10/2016.
 */

public class RefreshDataTask extends AsyncTask<Reserva,Integer,Void> {
    @Override
    protected Void doInBackground(Reserva... reserva) {
        DataHandler handler = DataHandler.getInstance();
        List<Reserva> listaReserva = handler.getList();
        reserva[0].setConfirmada(true);
        for (int i=0; i<listaReserva.size(); i++){
            if (reserva[0].getId().equals(listaReserva.get(i).getId())){
                listaReserva.set(i,reserva[0]);
            }
        }
        handler.setList(listaReserva);
        return null;
    }
}
