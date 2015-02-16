package com.antonio.android.gestoralumnos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Antonio on 16/12/2014.
 */
public class FragmentA extends Fragment {
    EditText etP,etD,etDp,etG,etLs;
    TextView tvHi,tvFi;
    Button bt,btV;
    String idAct,idProfesor,idGrupo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =getActivity().getLayoutInflater().inflate(R.layout.fragment_a,null);
        etP =(EditText)view.findViewById(R.id.etProAC);
        etG =(EditText)view.findViewById(R.id.etGrAC);
        etD=(EditText)view.findViewById(R.id.etDesAC);
        etDp=(EditText)view.findViewById(R.id.etDepAC);
        etLs=(EditText)view.findViewById(R.id.etALSC);
        tvHi=(TextView)view.findViewById(R.id.tvAHIC);
        tvFi=(TextView)view.findViewById(R.id.tvAFIC);
        bt=(Button)view.findViewById(R.id.btAAC);
        etDp.setEnabled(false);
        etP.setEnabled(false);
        etG.setEnabled(false);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etD.getText().toString().isEmpty()||!etP.getText().toString().isEmpty()||!tvFi.getText().toString().isEmpty()
                       ||!tvHi.getText().toString().isEmpty()){
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
                    String lugari=etLs.getText().toString();
                    try{
                        Actividad ac = new Actividad();
                        ac.setIdProfesor(idProf);
                        ac.setDescripcion(descp);
                        ac.setFechaI(fechai);
                        ac.setFechaF("");
                        ac.setLugarI(lugari);
                        ac.setLugarF("");
                        ac.setAlumno("antonio");
                        ac.setTipo("complementaria");
                        GestorPost post=new GestorPost();
                        Parametros p=new Parametros();
                        p.url="http://ieszv.x10.bz/restful/api/actividad";
                        p.jo=ac.getJSON();
                        post.execute(p);
                        Toast.makeText(getActivity(), "Actividad añadida", Toast.LENGTH_SHORT).show();
                        etP.setText("");
                        etG.setText("");
                        etD.setText("");
                        etDp.setText("");
                        etLs.setText("");
                        tvHi.setText("Hora inicio");
                        tvFi.setText("Fecha inicio");

                    }catch(Exception e){
                        System.out.println(e.toString()+" error ");
                    }
                }else{
                    Toast.makeText(getActivity(), "Rellene el formulario por favor", Toast.LENGTH_SHORT).show();

                }

            }
        });


        return view;
    }
    /***************************************************************/
/**********************METODO AUXILIAR************************/
    /***************************************************************/
    public String getId(String r){
        String idS="";
        String[]id1=r.split(":");
        if(id1.length>1) {
            idS = id1[1].toString();
            String[] id2 = idS.split("\\}");
            idS = id2[0].toString();
            System.out.println("id actividad " + idS);
        }
        return idS;
    }
    /***************************************************************/
/**********************Peticiones POST************************/
    /***************************************************************/
    public class GestorPost extends AsyncTask<Parametros,Void,String> {
        @Override
        protected String doInBackground(Parametros[] params) {
            String r=ClienteResFul.post(params[0].url,params[0].jo);

            idAct=getId(r);
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
                Toast.makeText(getActivity(), "Actividad Profesor añadida ", Toast.LENGTH_SHORT).show();
            }catch(Exception e){

            }
            try{
                ActividadGrupo ag = new ActividadGrupo(id, idGrupo);
                GestorPostP postag = new GestorPostP();
                Parametros pp = new Parametros();
                pp.url = "http://ieszv.x10.bz/restful/api/actividadgrupo";
                pp.jo = ag.getJSON();
                postag.execute(pp);
                Toast.makeText(getActivity(), "Actividad Grupo añadida ", Toast.LENGTH_SHORT).show();
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
