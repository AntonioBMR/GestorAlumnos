package com.antonio.android.gestoralumnos;

import org.json.JSONObject;

/**
 * Created by Antonio on 12/02/2015.
 */
public class Grupo {
    private String id, grupo;

    public Grupo() {
    }

    public Grupo(JSONObject object) {
        try {
            this.id = object.getString("id");
            this.grupo = object.getString("grupo");
        } catch (Exception e) {

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public JSONObject getJSONActividad(String idAct){
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("ida",idAct);
            jsonObject.put("idg",this.id);
            return jsonObject;
        }catch (Exception e){
            System.out.println("error aqui");
            return null;
        }
    }
}
