package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AltaReservaActivity extends AppCompatActivity {

    List<Reserva> listaReserva;
    ListView listViewReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        listaReserva = new ArrayList<>();
        listViewReserva = (ListView) findViewById(R.id.lvReserva);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Calendar calendario = Calendar.getInstance();
        Date inicio = calendario.getTime();
        calendario.add(Calendar.DAY_OF_MONTH,7);
        Date fin = calendario.getTime();
        calendario.add(Calendar.DAY_OF_MONTH,-7);
        Intent i = getIntent();
        Departamento dpto = (Departamento)i.getSerializableExtra("hotel");
        Reserva reserva = new Reserva(listaReserva.size()+1,inicio,fin,dpto);
        listaReserva.add(reserva);
        ReservaAdapter rAdapter = new ReservaAdapter(AltaReservaActivity.this,listaReserva);
        listViewReserva.setAdapter(rAdapter);
    }

    //TODO: implementar los metodos onSaveInstanceState y onRestoreInstanceState para que la lista de reservas sea persistente.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
