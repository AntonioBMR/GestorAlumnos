package com.antonio.android.gestoralumnos;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Fragmento2 extends Fragment {
    private final static String URLBASE = "http://ieszv.x10.bz/restful/api/";

    private View v;
    private ImageView iv;
    private TextView tvPro,tvDep;
    private String idp;
    public Fragmento2() {
    }

    public void setTexto(String texto,String texto2,String texto3,String texto4,String texto5,String texto6,String texto7){

        tvPro=(TextView)v.findViewById(R.id.tvProDM);
        idp=texto;
        TextView tv2=(TextView)v.findViewById(R.id.tvDesDM);
        tv2.setText(texto3);
        tvDep=(TextView)v.findViewById(R.id.tvDepDM);
        cargarDetalleP(idp);
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

    private void cargarDetalleP(String id) {
        String[] peticiones = new String[1];
        System.out.println("idprof"+id);
        peticiones[0] = "profesor/"+id;
        // peticiones[1] = "grupo/"+id;
        GetRestFulDetalle get = new GetRestFulDetalle();
        get.execute(peticiones);
    }

    private class GetRestFulDetalle extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String[] params) {

            String[] r = new String[params.length];
            int cont = 0;
            for (String s : params) {
                r[cont] = ClienteResFul.get(URLBASE + s);
                cont++;
            }
            return r;
        }

        @Override
        protected void onPostExecute(String[] r) {
            super.onPostExecute(r);
            System.out.println("idProfe " + r[0]);
            JSONTokener token = new JSONTokener(r[0]);
            String sp="";
            String sd="";
            try {
                JSONObject fila=(JSONObject) token.nextValue();
                Profesor p=new Profesor(fila);
                sp=p.getNombre()+" "+p.getApellidos();
                sd=p.getDepartamento();
                //JSONArray array = new JSONArray(token);
               /* for (int i = 0; i < array.length(); i++) {
                    JSONObject fila = array.getJSONObject(i);
                    Profesor p=new Profesor(fila);
                    sp=p.getNombre()+" "+p.getApellidos();
                    sd=p.getDepartamento();
                }*/

            } catch (JSONException je) {

            }
            tvPro.setText(getResources().getString(R.string.prof)+sp);
            tvDep.setText(getResources().getString(R.string.dep)+sd);
            System.out.println("prof"+"Profesor: "+sp+" "+sd+" ");

            /*try {
                JSONArray arrayE = new JSONArray(token);
                for (int i = 0; i < arrayE.length(); i++) {
                    JSONObject fila = arrayE.getJSONObject(i);
                    Grupo a = new Grupo(fila);
                    tvGrup.setText("Grupo: "+a.getGrupo());
                }
            } catch (JSONException je) {
            }*/
        }
    }
}





