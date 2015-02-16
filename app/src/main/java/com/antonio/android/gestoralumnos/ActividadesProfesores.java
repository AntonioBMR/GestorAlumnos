package com.antonio.android.gestoralumnos;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;


public class ActividadesProfesores extends Activity {
    private ListView lv;
    private ArrayList<ActividadProfesor>lista;
    AdaptadorActP adc;
    private final static String URLBASE = "http://ieszv.x10.bz/restful/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_profesores);
        lista=new ArrayList<ActividadProfesor>();
        lv=(ListView)findViewById(R.id.listViewAP);
        adc = new AdaptadorActP(ActividadesProfesores.this, R.layout.detalleactividadprofesores, lista);
        lv.setAdapter(adc);
        cargarActividadesP();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividades_profesores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /* cargar actividades*/
    private void cargarActividadesP() {
        String[] peticiones = new String[1];
        peticiones[0] = "actividadprofesor";
        GetRestFul get = new GetRestFul();
        get.execute(peticiones);
    }
    /**
     * ****ASYNCTASK hilos***********
     */
    private class GetRestFul extends AsyncTask<String, Void, String[]> {

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
            System.out.println("lista actividades "+r[0]);
            JSONTokener token = new JSONTokener(r[0]);
            ArrayList<Actividad> al=new ArrayList<Actividad>();
            lista=new ArrayList<ActividadProfesor>();
            try {
                JSONArray array = new JSONArray(token);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject fila = array.getJSONObject(i);
                    ActividadProfesor a = new ActividadProfesor(fila);
                    lista.add(a);
                    // actividades.add(a.getDescripcion()+"\n"+a.getFechaI());
                }
                if(!lista.isEmpty()){
                    lv = (ListView) findViewById(R.id.listViewAP);
                    adc = new AdaptadorActP(ActividadesProfesores.this, R.layout.detalleactividadprofesores, lista);
                    lv.setAdapter(adc);
                }

            } catch (JSONException je) {

            }
        }
    }
}
