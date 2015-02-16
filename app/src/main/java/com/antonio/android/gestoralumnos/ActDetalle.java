package com.antonio.android.gestoralumnos;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by Antonio on 11/02/2015.
 */
public class ActDetalle extends Activity {
    private final static String URLBASE = "http://ieszv.x10.bz/restful/api/";
    private String idPro,idGru;
    TextView tvPro,tvDep,tvGrup;
    boolean isPoG=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        String prof, grup, desc,dep,lui,luf,fei,fef,tip;
        idPro = getIntent().getStringExtra("idpro");
       // idGru = getIntent().getStringExtra("grupo");
        desc = getIntent().getStringExtra("desc");
        lui = getIntent().getStringExtra("lugari");
        fei = getIntent().getStringExtra("fechai");
        luf = getIntent().getStringExtra("lugarf");
        fef = getIntent().getStringExtra("fechaf");
        tip=getIntent().getStringExtra("tipo");
        tvPro=(TextView)findViewById(R.id.tvProDM);
        TextView tv2=(TextView)findViewById(R.id.tvDesDM);
        tv2.setText(desc);
        tvDep=(TextView)findViewById(R.id.tvDepDM);
        TextView tv4=(TextView)findViewById(R.id.tvLSDM);
        tv4.setText(getResources().getString(R.string.ls)+lui);
        TextView tv5=(TextView)findViewById(R.id.tvFIDM);
        tv5.setText(getResources().getString(R.string.fs)+fei);

       // cargarDetalleG(idGru);
        cargarDetalleP(idPro);
        if(tip.equals("complementaria")){
            LinearLayout ll=(LinearLayout)findViewById(R.id.lldetalle);
            ll.setBackgroundColor(Color.parseColor("#FFCC00"));
        }else{
            LinearLayout ll=(LinearLayout)findViewById(R.id.lldetalle);
            ll.setBackgroundColor(Color.parseColor("#FF4444"));
            TextView tv6=(TextView)findViewById(R.id.tvLLDM);
            tv6.setText(getResources().getString(R.string.ll)+luf);
            TextView tv7=(TextView)findViewById(R.id.tvFLDM);
            tv7.setText(getResources().getString(R.string.fs)+fef);
        }
        final Fragmento2 fdos = (Fragmento2) getFragmentManager().findFragmentById(R.id.fragment_2);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
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
            tvPro.setText(R.string.prof+sp);
            tvDep.setText(R.string.dep+sd);
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

    public Toast tostada(String t) {
        Toast toast =
                Toast.makeText(getApplicationContext(),
                        t + "", Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }
}
