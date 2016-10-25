package dam.isi.frsf.utn.edu.ar.laboratorio04;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.DataHandler;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.RefreshDataTask;

/**
 * Created by Pablo Paletto on 17/10/2016.
 */

public class Notificacion extends BroadcastReceiver {

    Intent resIntent = null;
    PendingIntent pi = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (System.currentTimeMillis()%3==0) {
            Reserva reserva = (Reserva) intent.getExtras().get("reserva");
            reserva.setConfirmada(true);
            new RefreshDataTask().execute(reserva);

            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nm = (NotificationManager) context.getSystemService(ns);
            int icon = R.mipmap.ic_launcher;
            resIntent = new Intent(context, AltaReservaActivity.class);
            resIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            resIntent.putExtra("source","second");
            pi = PendingIntent.getActivity(context, 0, resIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            Uri ringUri = Uri.parse(pref.getString("edit_ringtone","DEFAULT_RINGTONE_URI"));
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context.getApplicationContext())
                            .setSmallIcon(icon)
                            .setSound(ringUri)
                            .setContentIntent(pi)
                            .setContentTitle("Reserva")
                            .setContentText("Se ha confirmado una reserva")
                            .setAutoCancel(true);
            nm.notify(1, mBuilder.build());
        }
    }
}
