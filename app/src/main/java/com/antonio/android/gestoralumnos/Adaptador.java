package com.antonio.android.gestoralumnos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Antonio on 25/11/2014.
 */
public class Adaptador extends ArrayAdapter<String> {
    public static LayoutInflater i;
    public int resource;
    public List<String> lista;
    public TextView id,grupo, idProfesor, tipo, fechaI, fechaF, lugarI, lugarF, descripcion;

    public Adaptador (Context context,int resource,List<String> objects){
        super(context,resource,objects);
        this.resource=resource;
        lista=objects;
        this.i=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View v,ViewGroup vg){
        ViewHolder vh=null;
        if(v==null){
            v=i.inflate(resource,null);
            vh=new ViewHolder();
            vh.fechaI=(TextView)v.findViewById(R.id.tvFSD);
            vh.descripcion=(TextView)v.findViewById(R.id.tvDescD);
            v.setTag(vh);
        }else{
            vh=(ViewHolder)v.getTag();
        }
        vh.posicion=position;
        //vh.idProfesor.setText(lista.get(position));
        return v;
    }
    public static class ViewHolder{
        public TextView   fechaI,descripcion;
        public int posicion;
    }

}
