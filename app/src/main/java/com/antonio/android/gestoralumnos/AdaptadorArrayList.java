package com.antonio.android.gestoralumnos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Antonio on 17/12/2014.
 */
public class AdaptadorArrayList extends ArrayAdapter<Actividad> {
private Context contexto;
private ArrayList<Actividad> lista;
private int recurso;
private static LayoutInflater i;
private ViewHolder vh;

static class ViewHolder{
    public TextView tv,tv1,tv2;
    public LinearLayout ldetalle;
    public int posicion;
}
    public AdaptadorArrayList(Context context, int resource, ArrayList<Actividad> objects) {
        super(context, resource, objects);
        this.contexto=context;
        this.lista=objects;
        this.recurso=resource;
        this.i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = i.inflate(recurso, null);
            vh = new ViewHolder();
            vh.ldetalle=(LinearLayout)convertView.findViewById(R.id.ldetalle);

            vh.tv2=(TextView)convertView.findViewById(R.id.tvFSD);
            vh.tv1=(TextView)convertView.findViewById(R.id.tvDescD);
            //vh.tv=(TextView)convertView.findViewById(R.id.tvProfD);
            convertView.setTag(vh);
        }else{
            //comentario
            vh=(ViewHolder)convertView.getTag();
        }
        vh.posicion=position;
       if(lista.get(position).getTipo().equals("extraescolar")){
            vh.ldetalle.setBackgroundColor(Color.parseColor("#FF4444"));
        }else{
            vh.ldetalle.setBackgroundColor(Color.parseColor("#FFCC00"));

        }

       // vh.tv.setText(lista.get(position).getIdProfesor());
        vh.tv2.setText(lista.get(position).getFechaI());
        vh.tv1.setText(lista.get(position).getDescripcion());
        return convertView;
    }
}