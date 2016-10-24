package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.DataHandler;

public class AltaReservaActivity extends AppCompatActivity {

    private List<Reserva> listaReserva;
    private ListView listViewReserva;
    private DataHandler handler;
    private Boolean repetido = false;
    public static final int ALARM_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        listaReserva = new ArrayList<>();
        listViewReserva = (ListView) findViewById(R.id.lvReserva);
        handler = DataHandler.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (handler.getList() != null) listaReserva = handler.getList();
        Intent intent = getIntent();
        switch (intent.getExtras().get("source").toString()){
            case "first":
                Boolean esReserva = intent.getExtras().getBoolean("esReserva");
                if (esReserva) {
                    Calendar calendario = Calendar.getInstance();
                    Date inicio = calendario.getTime();
                    calendario.add(Calendar.DAY_OF_MONTH,7);
                    Date fin = calendario.getTime();
                    calendario.add(Calendar.DAY_OF_MONTH,-7);
                    Intent i = getIntent();
                    Departamento dpto = (Departamento)i.getSerializableExtra("hotel");
                    Reserva reserva = new Reserva(listaReserva.size()+1,inicio,fin,dpto);
                    for (Reserva list: listaReserva){
                        if (list.getAlojamiento().getId()==reserva.getAlojamiento().getId()) repetido = Boolean.TRUE;
                    }
                    if (!repetido) {
                        listaReserva.add(reserva);
                        handler.setList(listaReserva);
                        establecerAlarmaReserva(reserva);
                    }
                }
                else{
                    listaReserva = handler.getList();
                }
                ReservaAdapter rAdapter = new ReservaAdapter(AltaReservaActivity.this,this.listaReserva);
                try {
                    this.listViewReserva.setAdapter(rAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Exception--->",e.getMessage());
                    this.listViewReserva.setAdapter(null);
                    Toast.makeText(getApplicationContext(),"Lista vacía",Toast.LENGTH_LONG).show();
                }
                break;

            case "second":
                listaReserva = handler.getList();
                rAdapter = new ReservaAdapter(AltaReservaActivity.this,this.listaReserva);
                this.listViewReserva.setAdapter(rAdapter);
                break;
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        handler.setList(listaReserva);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        handler.setList(listaReserva);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listaReserva = handler.getList();
    }

    private void establecerAlarmaReserva(Reserva reserva){
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,Notificacion.class);
        intent.putExtra("reserva",reserva);
        PendingIntent pIntent = PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        manager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+15000,pIntent);
    }

}
