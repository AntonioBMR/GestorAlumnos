package com.antonio.android.gestoralumnos;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Antonio on 16/12/2014.
 */



public class Secundaria extends FragmentActivity {
    private static final int ACTIVIDAD_SEGUNDA = 1;
    private ViewPager viewPager=null;
    private EditText etP,etD,etDp,etG,etLs,etLl;
    private TextView tvHi,tvHf,tvFi,tvFf;
    private final static String URLBASE = "http://ieszv.x10.bz/restful/api/";
    private ArrayList<String> profes;
    private ArrayList<Profesor>profesores;
    private ArrayList<String> grupos;
    private ArrayList<Grupo> gruposG;
    ArrayAdapter<String> adapter;
/***************************************************************/
/**********************OnCReate************************/
    /***************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secundaria);
        viewPager= (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));
        profes = new ArrayList<String>();
        profesores = new ArrayList<Profesor>();
        grupos = new ArrayList<String>();
        gruposG = new ArrayList<Grupo>();
        cargar();
        etP =(EditText)findViewById(R.id.etProAE);
        etG =(EditText)findViewById(R.id.etGrAE);
        etD=(EditText)findViewById(R.id.etDesAE);
        etDp=(EditText)findViewById(R.id.etDepAE);
        etLs=(EditText)findViewById(R.id.etALSE);
        etLl=(EditText)findViewById(R.id.etALLE);
        tvHi=(TextView)findViewById(R.id.tvAHIE);
        tvHf=(TextView)findViewById(R.id.tvAHLE);
        tvFi=(TextView)findViewById(R.id.tvAFIE);
        tvFf=(TextView)findViewById(R.id.tvAFLE);

    }
    /***************************************************************/
/**********************BOTONES************************/
    /***************************************************************/
    public void volver(View v){
        Intent intent = new Intent(Secundaria.this, MainActivity.class);
        setResult(ACTIVIDAD_SEGUNDA, intent);
        Secundaria.this.finish();
    }
    public void volverC(View v){
        Intent intent = new Intent(Secundaria.this, MainActivity.class);
        setResult(ACTIVIDAD_SEGUNDA, intent);
        Secundaria.this.finish();
    }

/***************************************************************/
/**********************FRAGMENTOS ACTIVVIDADES************************/
/***************************************************************/

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            if (i == 0) {
                fragment = new FragmentA();
            }
            if (i == 1) {
                fragment = new FragmentB();
            }
            return fragment;
        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Act. Complementarias";
            }
            if (position == 1) {
                return "Act. Extraescolares";
            }

            return null;
        }
    }

/***************************************************************/
/**********************DATEYTIMEPICKERSS************************/
/***************************************************************/

    public void horaInicioE(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),"horaDe");

    }
    public void horaInicioC(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),"horaI");

    }
    public void horaLlegadaE(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),"horaHasta");

    }
    public void fechaLlegadaE(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"fechaHasta");

    }
    public void fechaInicioE(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"fechaDe");

    }
    public void fechaInicioC(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"fechaI");

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
                TextView tvHorai = (TextView) getActivity().findViewById(R.id.tvAHIE);
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
            if(getTag().toString().compareTo("horaI")==0){
                TextView tvHorai = (TextView) getActivity().findViewById(R.id.tvAHIC);
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
                TextView tvHoral = (TextView) getActivity().findViewById(R.id.tvAHLE);
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
                TextView tvFi = (TextView) getActivity().findViewById(R.id.tvAFIE);
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
            if(getTag().toString().compareTo("fechaI")==0){
                TextView tvFi = (TextView) getActivity().findViewById(R.id.tvAFIC);
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
                TextView tv = (TextView) getActivity().findViewById(R.id.tvAFLE);
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
/*******************************************************************/
/*****************PETICIONES GET A REST Spinners*********************/
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
            Spinner mySpinner = (Spinner) findViewById(R.id.spinnerAPE);
            Spinner mySpinner1 = (Spinner) findViewById(R.id.spinnerAGE);
            Spinner mySpinnerC = (Spinner) findViewById(R.id.spinnerAPC);
            Spinner mySpinnerC1 = (Spinner) findViewById(R.id.spinnerAGC);
            mySpinner.setAdapter(new ArrayAdapter<String>(Secundaria.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    profes));
            mySpinner1.setAdapter(new ArrayAdapter<String>(Secundaria.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    grupos));
            mySpinnerC.setAdapter(new ArrayAdapter<String>(Secundaria.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    profes));
            mySpinnerC1.setAdapter(new ArrayAdapter<String>(Secundaria.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    grupos));
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0,
                                                   View arg1, int position, long arg3) {
                            // TODO Auto-generated method stub
                            // Locate the textviews in activity_main.xml
                            EditText tvProE = (EditText) findViewById(R.id.etProAE);
                            EditText tvDep = (EditText) findViewById(R.id.etDepAE);
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
                            EditText tvGruE = (EditText) findViewById(R.id.etGrAE);
                            // Set the text followed by the position
                            tvGruE.setText(gruposG.get(position).getId().toString()+"-"+gruposG.get(position).getGrupo());
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
            mySpinnerC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0,
                                           View arg1, int position, long arg3) {
                    // TODO Auto-generated method stub
                    // Locate the textviews in activity_main.xml
                    EditText tvProE = (EditText) findViewById(R.id.etProAC);
                    EditText tvDep = (EditText) findViewById(R.id.etDepAC);

                    // Set the text followed by the position
                    tvProE.setText(profes.get(position).toString());
                    tvDep.setText(profesores.get(position).getDepartamento());

                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });
            mySpinnerC1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0,
                                           View arg1, int position, long arg3) {
                    // TODO Auto-generated method stub
                    // Locate the textviews in activity_main.xml
                    EditText tvGruE = (EditText) findViewById(R.id.etGrAC);
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


    }


