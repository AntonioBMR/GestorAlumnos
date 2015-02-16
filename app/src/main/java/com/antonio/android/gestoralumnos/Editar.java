package com.antonio.android.gestoralumnos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Calendar;


public class Editar extends FragmentActivity{
    private static final int ACTIVIDAD_EDITAR = 4;
    EditText etP,etD,etDp,etG,etLs,etLl;
    TextView tvHi,tvHf,tvFi,tvFf;
    String idAct,idProfesor,idGrupo;
    Button bt,btV;
    private final static String URLBASE = "http://ieszv.x10.bz/restful/api/";
    private ArrayList<String> profes;
    private ArrayList<Profesor>profesores;
    private ArrayList<String> grupos;
    private ArrayList<Grupo> gruposG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idAct=getIntent().getExtras().getString("id");
        setContentView(R.layout.actividad_editar);
        profes = new ArrayList<String>();
        profesores = new ArrayList<Profesor>();
        grupos = new ArrayList<String>();
        gruposG = new ArrayList<Grupo>();
        cargar();
        etP =(EditText)findViewById(R.id.etProAEditar);
        etG =(EditText)findViewById(R.id.etGrAEditar);
        etD=(EditText)findViewById(R.id.etDesAEditar);
        etDp=(EditText)findViewById(R.id.etDepAEditar);
        etDp.setEnabled(false);
        etP.setEnabled(false);
        etG.setEnabled(false);
        etLs=(EditText)findViewById(R.id.etALSEditar);
        etLl=(EditText)findViewById(R.id.etALLEditar);
        tvHi=(TextView)findViewById(R.id.tvAHIEditar);
        tvHf=(TextView)findViewById(R.id.tvAHLEditar);
        tvFi=(TextView)findViewById(R.id.tvAFIEditar);
        tvFf=(TextView)findViewById(R.id.tvAFLEditar);
        bt=(Button)findViewById(R.id.btAAEditar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etD.getText().toString().isEmpty()&&!etP.getText().toString().isEmpty()&&!tvFi.getText().toString().isEmpty()
                        &&!tvFf.getText().toString().isEmpty()&&!tvHi.getText().toString().isEmpty()&&
                        !tvHf.getText().toString().isEmpty()){
                        String descp=etD.getText().toString();
                        String prof=etP.getText().toString();
                        String[] parts = prof.split("-");
                        String idProf = parts[0].toString();
                        idProfesor=idProf;
                        String grup=etG.getText().toString();
                        String[] partes = grup.split("-");
                        String idGrup = partes[0].toString();
                        idGrupo=idGrup;
                        String fechai=tvFi.getText().toString() + " " + tvHi.getText().toString();
                        String fechaf=tvFf.getText().toString() + " " + tvHf.getText().toString();
                        String lugari=etLs.getText().toString();
                        String lugarl=etLl.getText().toString();

                        try{

                            if(etLl.getText().toString().isEmpty() && fechaf.equals("Fecha Llegada"+" "+"Hora llegada")){
                                Actividad ac = new Actividad();
                                ac.setId(idAct);
                                ac.setIdProfesor(idProf);
                                ac.setDescripcion(descp);
                                ac.setFechaF("");
                                ac.setTipo("complementaria");
                                ac.setFechaI(fechai);
                                ac.setLugarI(lugari);
                                ac.setLugarF(lugarl);
                                ac.setAlumno("antonio");
                                GestorPut put=new GestorPut();
                                Parametros p=new Parametros();
                                p.url="http://ieszv.x10.bz/restful/api/actividad";
                                p.jo=ac.getJSON();
                                put.execute(p);
                                System.out.println("idactividad" + idAct);
                                etP.setText("");
                                etG.setText("");
                                etD.setText("");
                                etDp.setText("");
                                etLs.setText("");
                                etLl.setText("");
                                tvHi.setText("Hora inicio");
                                tvHf.setText("Hora llegada");
                                tvFi.setText("Fecha inicio");
                                tvFf.setText("Fecha llegada");
                                Toast.makeText(Editar.this, "Actividad editada ", Toast.LENGTH_SHORT).show();
                                Editar.this.finish();

                            }else if(!etLl.getText().toString().isEmpty() &&(!tvFf.getText().toString().equals("Fecha Llegada"))
                                    &&(!tvHf.getText().toString().equals("Hora llegada"))){
                                Actividad ac = new Actividad();
                                ac.setId(idAct);
                                ac.setIdProfesor(idProf);
                                ac.setDescripcion(descp);
                                ac.setFechaF(fechaf);
                                ac.setTipo("extraescolar");
                                ac.setFechaI(fechai);
                                ac.setLugarI(lugari);
                                ac.setLugarF(lugarl);
                                ac.setAlumno("antonio");
                                GestorPut put=new GestorPut();
                                Parametros p=new Parametros();
                                p.url="http://ieszv.x10.bz/restful/api/actividad";
                                p.jo=ac.getJSON();
                                put.execute(p);
                                System.out.println("idactividad" + idAct);
                                etP.setText("");
                                etG.setText("");
                                etD.setText("");
                                etDp.setText("");
                                etLs.setText("");
                                etLl.setText("");
                                tvHi.setText("Hora inicio");
                                tvHf.setText("Hora llegada");
                                tvFi.setText("Fecha inicio");
                                tvFf.setText("Fecha llegada");
                                Toast.makeText(Editar.this, "Actividad editada ", Toast.LENGTH_SHORT).show();
                                Editar.this.finish();
                            }else{
                                Toast.makeText(Editar.this, "Revise los campos ", Toast.LENGTH_SHORT).show();

                            }


                        }catch(Exception e){
                            System.out.println(e.toString()+" error Put");
                        }


                }else{
                    Toast.makeText(Editar.this, "Revise los campos ", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
/*******************************************************************/
/**********************PETICIONES GET A REST************************/
    /********************************************************************/
    /* cargar profesy grupos en Spinners*/
    private void cargar() {
        String[] peticiones = new String[2];
        // peticiones[0] = "actividad/juanito";
        peticiones[0] = "profesor";
        peticiones[1] = "grupo";
        GetRestFul get = new GetRestFul();
        get.execute(peticiones);
    }
    /**
     * ****ASYNCTASK hilosgetProfesoresSpinner***********
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
            try {
                JSONArray array = new JSONArray(token);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject fila = array.getJSONObject(i);
                    Profesor a = new Profesor(fila);
                    profes.add(a.getId()+"-"+a.getNombre()+" "+a.getApellidos());
                    profesores.add(a);

                }
            } catch (JSONException je) {
            }
            JSONTokener tokenG = new JSONTokener(r[1]);
            try {
                JSONArray arrayE = new JSONArray(tokenG);
                for (int i = 0; i < arrayE.length(); i++) {
                    JSONObject fila = arrayE.getJSONObject(i);
                    Grupo a = new Grupo(fila);
                    grupos.add(a.getGrupo());
                    gruposG.add(a);
                }
            } catch (JSONException je) {
            }
            Spinner mySpinner = (Spinner) findViewById(R.id.spinnerAPEditar);
            Spinner mySpinner1 = (Spinner) findViewById(R.id.spinnerAGEditar);
            mySpinner.setAdapter(new ArrayAdapter<String>(Editar.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    profes));
            mySpinner1.setAdapter(new ArrayAdapter<String>(Editar.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    grupos));

            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0,
                                           View arg1, int position, long arg3) {
                    // TODO Auto-generated method stub
                    // Locate the textviews in activity_main.xml
                    EditText tvProE = (EditText) findViewById(R.id.etProAEditar);
                    EditText tvDep = (EditText) findViewById(R.id.etDepAEditar);
                    // Set the text followed by the position
                    tvProE.setText(profes.get(position).toString());
                    tvDep.setText(profesores.get(position).getDepartamento());

                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });
            mySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0,
                                           View arg1, int position, long arg3) {
                    // TODO Auto-generated method stub
                    // Locate the textviews in activity_main.xml
                    EditText tvGruE = (EditText) findViewById(R.id.etGrAEditar);
                    // Set the text followed by the position
                    tvGruE.setText(gruposG.get(position).getId().toString()+"-"+gruposG.get(position).getGrupo());
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });


        }
    }


    public void volver(View v){
        Intent intent = new Intent(Editar.this, MainActivity.class);
        setResult(ACTIVIDAD_EDITAR, intent);
        Editar.this.finish();
    }
    /***************************************************************/
/**********************DATEYTIMEPICKERSS************************/
    /***************************************************************/

    public void horaInicioEditar(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),"horaDe");

    }
    public void fechaInicioE(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"fechaDe");

    }
    public void horaLlegadaE(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),"horaHasta");

    }
    public void fechaLlegadaE(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"fechaHasta");

    }

    public static  class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            if(getTag().toString().compareTo("horaDe")==0){
                TextView tvHorai = (TextView) getActivity().findViewById(R.id.tvAHIEditar);
                String h=hourOfDay+"";
                if(h.length()==1){
                    h=0+h;
                }
                String m=minute+"";
                if(m.length()==1){
                    m=0+m;
                }
                tvHorai.setText(h + ":" + m);
            }

            else if(getTag().toString().compareTo("horaHasta")==0){
                TextView tvHoral = (TextView) getActivity().findViewById(R.id.tvAHLEditar);
                String h=hourOfDay+"";
                if(h.length()==1){
                    h=0+h;
                }
                String m=minute+"";
                if(m.length()==1){
                    m=0+m;
                }
                tvHoral.setText(h + ":" + m);            }

        }
    }

    public static   class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the pickere
            final Calendar c = Calendar.getInstance();
            int mes=c.get(Calendar.MONTH);
            int ano=c.get(Calendar.YEAR);
            int dia=c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if(getTag().toString().compareTo("fechaDe")==0){
                TextView tvFi = (TextView) getActivity().findViewById(R.id.tvAFIEditar);
                monthOfYear=monthOfYear+1;
                String m=monthOfYear+"";
                if(m.length()==1){
                    m=0+m;
                }
                String d=dayOfMonth+"";
                if(d.length()==1){
                    d=0+d;
                }
                tvFi.setText(year + "-" + m+"-"+d);
            }

            else if(getTag().toString().compareTo("fechaHasta")==0){
                TextView tv = (TextView) getActivity().findViewById(R.id.tvAFLEditar);
                monthOfYear=monthOfYear+1;
                String m=monthOfYear+"";
                if(m.length()==1){
                    m=0+m;
                }
                String d=dayOfMonth+"";
                if(d.length()==1){
                    d=0+d;
                }
                tv.setText(year + "-" + m+"-"+d);
            }
        }
    }
    public class GestorPut extends AsyncTask<Parametros,Void,String> {
        @Override
        protected String doInBackground(Parametros[] params) {
            String r=ClienteResFul.put(params[0].url, params[0].jo);

            System.out.println(idAct+"  esto queda");
            return r;
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);
            String id=idAct;
            System.out.println("id act"+id+" "+idProfesor);

            try {
                ActividadProfesor ag = new ActividadProfesor(id, idProfesor);
                GestorPostP postag = new GestorPostP();
                Parametros pp = new Parametros();
                pp.url = "http://ieszv.x10.bz/restful/api/actividadprofesor";
                pp.jo = ag.getJSON();
                postag.execute(pp);
            }catch(Exception e){

            }
            try{
                ActividadGrupo ag = new ActividadGrupo(id, idGrupo);
                GestorPostP postag = new GestorPostP();
                Parametros pp = new Parametros();
                pp.url = "http://ieszv.x10.bz/restful/api/actividadgrupo";
                pp.jo = ag.getJSON();
                postag.execute(pp);
                System.out.println("id act"+id+" "+idProfesor+idGrupo);
                idAct="";

            }catch (Exception e){

            }
        }


    }
    public class GestorPostP extends AsyncTask<Parametros,Void,String> {
        @Override
        protected String doInBackground(Parametros[] params) {
            String r=ClienteResFul.post(params[0].url,params[0].jo);

            return r;
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);


        }


    }
}
