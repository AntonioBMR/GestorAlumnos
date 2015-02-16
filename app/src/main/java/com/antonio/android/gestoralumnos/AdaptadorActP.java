package com.antonio.android.gestoralumnos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Antonio on 15/02/2015.
 */
public class AdaptadorActP extends ArrayAdapter<ActividadProfesor> {
    private Context contexto;
    private ArrayList<ActividadProfesor> lista;
    private int recurso;
    private static LayoutInflater i;
    private ViewHolder vh;

    static class ViewHolder{
        public TextView tv;
        public int posicion;
    }
    public AdaptadorActP(Context context, int resource, ArrayList<ActividadProfesor> objects) {
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
            vh.tv=(TextView)convertView.findViewById(R.id.tvDetalleActP);
            //vh.tv=(TextView)convertView.findViewById(R.id.tvProfD);
            convertView.setTag(vh);
        }else{
            //comentario
            vh=(ViewHolder)convertView.getTag();
        }
        vh.posicion=position;


        // vh.tv.setText(lista.get(position).getIdProfesor());
        vh.tv.setText(lista.get(position).getId().toString()+" "+lista.get(position).getIdprofesor().toString()+lista.get(position).getIdactividad().toString());
        return convertView;
    }

}