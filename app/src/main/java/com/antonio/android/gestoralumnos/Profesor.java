package com.antonio.android.gestoralumnos;

import org.json.JSONObject;

/**
 * Created by Antonio on 12/02/2015.
 */
public class Profesor {

    private String id, nombre, apellidos, departamento;

    public Profesor() {
    }

    public Profesor(JSONObject object) {
        try {
            this.id = object.getString("id");
            this.nombre = object.getString("nombre");
            this.apellidos=object.getString("apellidos");
            this.departamento=object.getString("departamento");
        } catch (Exception e) {

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public JSONObject getJSONActividad(String idAct){
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("ida",idAct);
            jsonObject.put("idp",this.id);
            return jsonObject;
        }catch (Exception e){
            System.out.println("error aqui");
            return null;
        }
    }

}