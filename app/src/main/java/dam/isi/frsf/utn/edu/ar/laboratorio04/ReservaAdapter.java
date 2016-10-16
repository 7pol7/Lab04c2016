package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.Inflater;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by Pablo Paletto on 15/10/2016.
 */

public class ReservaAdapter extends ArrayAdapter<Reserva> {
    private LayoutInflater inflater;
    private Context context;

    public ReservaAdapter(Context context, List<Reserva> items){
        super(context, R.layout.fila_reserva,items);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("##.##");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        View fila = convertView;
        if (fila == null){
            fila = inflater.inflate(R.layout.fila_reserva,parent,false);
        }
        ViewHolder holder = (ViewHolder)fila.getTag();
        if (holder == null){
            holder = new ViewHolder(fila);
            fila.setTag(holder);
        }
        holder.tvDepto.setText(this.getItem(position).getAlojamiento().getId().toString());
        holder.tvPrecio.setText("$ " + df.format(this.getItem(position).getPrecio()));
        holder.tvFechaInicio.setText(dateFormat.format(this.getItem(position).getFechaInicio()));
        holder.tvFechaFin.setText(dateFormat.format(this.getItem(position).getFechaFin()));
        if (holder.cbReserva.isChecked()){
            holder.cbReserva.setChecked(true);
        }

        return fila;
    }

    class ViewHolder{
        TextView tvDepto = null;
        TextView tvPrecio = null;
        TextView tvFechaInicio = null;
        TextView tvFechaFin = null;
        CheckBox cbReserva = null;

        ViewHolder(View base){
            this.tvDepto = (TextView)base.findViewById(R.id.tvDepto);
            this.tvPrecio = (TextView)base.findViewById(R.id.tvPrecio);
            this.tvFechaInicio = (TextView)base.findViewById(R.id.tvFechaInicio);
            this.tvFechaFin = (TextView)base.findViewById(R.id.tvFechaFin);
            this.cbReserva = (CheckBox)base.findViewById(R.id.cbReserva);
        }
    }
}
