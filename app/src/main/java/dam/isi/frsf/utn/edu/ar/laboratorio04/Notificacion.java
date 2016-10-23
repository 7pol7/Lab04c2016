package dam.isi.frsf.utn.edu.ar.laboratorio04;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by Pablo Paletto on 17/10/2016.
 */

public class Notificacion extends BroadcastReceiver {

    @Override
    //TODO:Crear la notificación y hacer que la reserva se cumpla según enunciado.
    public void onReceive(Context context, Intent intent) {
        Reserva reserva = (Reserva) intent.getExtras().get("reserva");
        reserva.setConfirmada(true);
        Intent resIntent = new Intent(context, AltaReservaActivity.class);
        resIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        resIntent.putExtra("source","second");
        resIntent.putExtra("resReserva",reserva);
        Toast.makeText(context,"Han pasado 15 segundos",Toast.LENGTH_LONG).show();
        context.startActivity(resIntent);
    }

    private void sendNotification (Context context, String message){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nm = (NotificationManager) context.getSystemService(ns);
        int icon = R.mipmap.ic_launcher;
        Intent intent = new Intent(AltaReservaActivity.ACTIVITY_SERVICE);
        //PendingIntent pi = PendingIntent.getActivities(context, 0, intent, 0);
    }
}
