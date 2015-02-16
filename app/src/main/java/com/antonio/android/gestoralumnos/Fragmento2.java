package com.antonio.android.gestoralumnos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragmento2 extends Fragment {
    private View v;
    private ImageView iv;
    public Fragmento2() {
    }

    public void setTexto(String texto,String texto1,String texto2,String texto3,String texto4,String texto5,String texto6,String texto7){
        TextView tv=(TextView)v.findViewById(R.id.tvProDM);
        tv.setText("");
        TextView tv2=(TextView)v.findViewById(R.id.tvDesDM);
        tv2.setText(texto2);
        TextView tv3=(TextView)v.findViewById(R.id.tvDepDM);
        tv3.setText("");
        TextView tv4=(TextView)v.findViewById(R.id.tvLSDM);
        tv4.setText(texto4);
        TextView tv5=(TextView)v.findViewById(R.id.tvFIDM);
        tv5.setText(texto5);
        TextView tv6=(TextView)v.findViewById(R.id.tvLLDM);
        tv6.setText(texto6);
        TextView tv7=(TextView)v.findViewById(R.id.tvFLDM);
        tv7.setText(texto7);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_2,container,false);
        //iv =(ImageView) v.findViewById(R.id.imageView1);
        return v;
    }


}





