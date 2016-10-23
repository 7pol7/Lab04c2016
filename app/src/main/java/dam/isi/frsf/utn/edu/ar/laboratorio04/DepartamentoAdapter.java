package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;

public class DepartamentoAdapter extends ArrayAdapter<Departamento> {
    private LayoutInflater inflater;
    private Context contexto;

    public DepartamentoAdapter(Context contexto, List<Departamento> items) {
        super(contexto, R.layout.fila, items);
        inflater = LayoutInflater.from(contexto);
        this.contexto=contexto;
    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.##");
        View row = convertView;
        if (row == null) row = inflater.inflate(R.layout.fila, parent, false);

        ViewHolder holder = (ViewHolder)row.getTag();
        if (holder==null){
            holder=new ViewHolder(row);
            row.setTag(holder);
        }

        holder.txtCiudad.setText(this.getItem(position).getCiudad().getNombre());
        holder.txtDescripcion.setText("Unico!! " + this.getItem(position).getDescripcion());
        holder.txtPrecio.setText("$" + (df.format(this.getItem(position).getPrecio())));
        holder.txtCapacidad.setText(this.getItem(position).getCapacidadMaxima()+".");

        holder.btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),getItem(position).getPrecio().toString(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(),AltaReservaActivity.class);
                i.putExtra("hotel",getItem(position));
                i.putExtra("source","first");
                i.putExtra("esReserva",true);
                contexto.startActivity(i);
            }
        });

        /*
        //DEPRECATED
        TextView txtCiudad = (TextView) row.findViewById(R.id.ciudad);
        txtCiudad.setText(this.getItem(position).getCiudad().getNombre());
        TextView txtDescripcion = (TextView) row.findViewById(R.id.descripcion);
        txtDescripcion.setText("Unico!! " + this.getItem(position).getDescripcion());
        TextView txtPrecio = (TextView) row.findViewById(R.id.precio);
        txtPrecio.setText("$" + (df.format(this.getItem(position).getPrecio())));
        TextView txtCapacidad = (TextView) row.findViewById(R.id.capacidadMax);
        txtCapacidad.setText(this.getItem(position).getCapacidadMaxima()+".");
        Button btnReservar = (Button) row.findViewById(R.id.btnReserva);*/

        return (row);

    }

    class ViewHolder{
        TextView txtCiudad = null;
        TextView txtDescripcion = null;
        TextView txtPrecio = null;
        TextView txtCapacidad = null;
        Button btnReservar = null;

        ViewHolder(View base){
            this.txtCiudad = (TextView)base.findViewById(R.id.ciudad);
            this.txtDescripcion = (TextView)base.findViewById(R.id.descripcion);
            this.txtPrecio = (TextView)base.findViewById(R.id.precio);
            this.txtCapacidad = (TextView)base.findViewById(R.id.capacidadMax);
            this.btnReservar = (Button)base.findViewById(R.id.btnReserva);
        }
    }
}
