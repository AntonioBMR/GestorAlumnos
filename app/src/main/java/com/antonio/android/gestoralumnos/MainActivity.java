package com.antonio.android.gestoralumnos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends Activity implements AdapterView.OnItemLongClickListener {
    private static final int ACTIVIDAD_SEGUNDA = 1;
    private static final int ACTIVIDAD_EDITAR = 4;
    private ArrayList<Actividad> lista;
    private AdaptadorArrayList adc;
    private ListView lv;
    private final int ACTIVIDAD2 = 3;
    private final static String URLBASE = "http://ieszv.x10.bz/restful/api/";
    private ArrayList<String> actividades;
/***************************************************************/
/**********************ONCREATE********************************/
/***************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = new ArrayList<Actividad>();
        actividades = new ArrayList<String>();
        cargarActividades();
        lv = (ListView) findViewById(R.id.listView);
        adc = new AdaptadorArrayList(MainActivity.this, R.layout.detalle, lista);
        lv.setAdapter(adc);
        final Fragmento2 fdos = (Fragmento2) getFragmentManager().findFragmentById(R.id.fragment_2);
        final boolean horizontal;
        if (fdos != null && fdos.isInLayout()) {
            horizontal = true;
        } else {
            horizontal = false;
        }
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (horizontal) {
                    fdos.setTexto(lista.get(position).getIdProfesor(), "", lista.get(position).getDescripcion(), "",
                            lista.get(position).getLugarI(), lista.get(position).getFechaI(), lista.get(position).getLugarF(), lista.get(position).getFechaF());
                } else {
                    Intent i = new Intent(MainActivity.this, ActDetalle.class);
                    i.putExtra("idpro", lista.get(position).getIdProfesor());
                    i.putExtra("tipo",lista.get(position).getTipo());
                    i.putExtra("desc", lista.get(position).getDescripcion());
                    i.putExtra("lugari", lista.get(position).getLugarI());
                    i.putExtra("fechai", lista.get(position).getFechaI());
                    i.putExtra("lugarf", lista.get(position).getLugarF());
                    i.putExtra("fechaf", lista.get(position).getFechaF());
                    startActivityForResult(i, ACTIVIDAD2);
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual, menu);

    }
    /***************************************************************/
/**********************MENU ACTIVVIDADES************************/
    /***************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.agregar) {
            Intent i = new Intent(MainActivity.this, Secundaria.class);
            startActivityForResult(i, ACTIVIDAD_SEGUNDA);
            return true;
        }
        if (id == R.id.actP) {
            Intent i = new Intent(MainActivity.this, ActividadesProfesores.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.ordena) {
            Collections.sort(lista);
            lv.setAdapter(adc);
            adc.notifyDataSetChanged();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

/***************************************************************/
/**********************BORRAR ACTIVVIDADES************************/
    /***************************************************************/
    public void borrar(final String ids, final int pos) {

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
        dialogo1.setTitle(getResources().getString(R.string.importante));
        dialogo1.setMessage(getResources().getString(R.string.desBorrar));
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(getResources().getString(R.string.conf), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                String url = "http://ieszv.x10.bz/restful/api/actividad/";
                //String ids=lista.get(id).getId() + "";
                GestorDelete gd = new GestorDelete();
                gd.execute(url + ids);
                lista.remove(pos);
                adc.notifyDataSetChanged();

            }
        });
        dialogo1.setNegativeButton(getResources().getString(R.string.canc), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.cancel();
            }
        });
        dialogo1.show();
    }

/***************************************************************/
/**********************OnACtivityResult ACTIVVIDADES************************/
    /***************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVIDAD_SEGUNDA) {
            cargarActividades();
        }
        if (requestCode == ACTIVIDAD_EDITAR) {
            cargarActividades();
        }

    }
/***************************************************************/
/**********************Get REST carga ListView ACTIVVIDADES************************/
    /***************************************************************/
    /* cargar actividades*/
    private void cargarActividades() {
        String[] peticiones = new String[3];
        peticiones[0] = "actividad/antonio";
        peticiones[1] = "profesor";
        peticiones[2] = "grupo";
        GetRestFul get = new GetRestFul();
        get.execute(peticiones);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String[] opc = new String[]{getString(R.string.Borrar), getString(R.string.editar)};
        final int posicion = position;
        AlertDialog opciones = new AlertDialog.Builder(
                MainActivity.this)
                .setTitle(getString(R.string.opciones))
                .setItems(opc,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int selected) {
                                if (selected == 0) {
                                    borrar(lista.get(posicion).getId(), posicion);
                                } else if (selected == 1) {
                                   Intent i =new Intent(MainActivity.this,Editar.class);
                                    i.putExtra("id",lista.get(posicion).getId());
                                    startActivityForResult(i,ACTIVIDAD_EDITAR);
                                }
                            }
                        }).create();
        opciones.show();

        return true;
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
            JSONTokener token = new JSONTokener(r[0]);
            ArrayList<Actividad> al = new ArrayList<Actividad>();
            lista = new ArrayList<Actividad>();
            try {
                JSONArray array = new JSONArray(token);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject fila = array.getJSONObject(i);
                    Actividad a = new Actividad(fila);
                    al.add(a);
                }
                for (int j = 0; j < al.size(); j++) {
                    lista.add(al.get(j));
                }
                lv = (ListView) findViewById(R.id.listView);
                adc = new AdaptadorArrayList(MainActivity.this, R.layout.detalle, lista);
                lv.setAdapter(adc);
                adc.notifyDataSetChanged();
            } catch (JSONException je) {

            }
        }
    }

}