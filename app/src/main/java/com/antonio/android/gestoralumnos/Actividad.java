package com.antonio.android.gestoralumnos;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Antonio on 17/12/2014.
 */
public class Actividad implements Comparable<Actividad> {
    private String id,alumno, idProfesor, tipo, fechaI, fechaF, lugarI, lugarF, descripcion;
    public Actividad(){

    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public Actividad(JSONObject js) {
        try {
            this.id = js.getString("id");
            this.idProfesor = js.getString("idprofesor");
            this.tipo = js.getString("tipo");
            this.fechaI = js.getString("fechai");
            this.fechaF = js.getString("fechaf");
            this.lugarI = js.getString("lugari");
            this.lugarF = js.getString("lugarf");
            this.alumno = js.getString("alumno");
            this.descripcion = js.getString("descripcion");
        } catch (JSONException je) {

        }
    }

    public Actividad(String id,String alumno, String idProfesor, String tipo, String fechaI, String fechaF, String lugarI, String lugarF, String descripcion) {
        this.id = id;
        this.alumno = alumno;
        this.idProfesor = idProfesor;
        this.tipo = tipo;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.lugarI = lugarI;
        this.lugarF = lugarF;
        this.descripcion = descripcion;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    public String getLugarI() {
        return lugarI;
    }

    public void setLugarI(String lugarI) {
        this.lugarI = lugarI;
    }

    public String getLugarF() {
        return lugarF;
    }

    public void setLugarF(String lugarF) {
        this.lugarF = lugarF;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", idProfesor='" + idProfesor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaI='" + fechaI + '\'' +
                ", fechaF='" + fechaF + '\'' +
                ", lugarI='" + lugarI + '\'' +
                ", lugarF='" + lugarF + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", alumno='" + alumno + '\'' +
                '}';
    }
    public JSONObject getJSON(){
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("id",this.id);
            jsonObject.put("idprofesor",this.idProfesor);
            jsonObject.put("tipo",this.tipo);
            jsonObject.put("fechai",this.fechaI);
            jsonObject.put("fechaf",this.fechaF);
            jsonObject.put("lugari",this.lugarI);
            jsonObject.put("lugarf",this.lugarF);
            jsonObject.put("descripcion",this.descripcion);
            jsonObject.put("alumno",this.alumno);
            return jsonObject;
        }catch (Exception e){
            System.out.println("error aqui");
            return null;
        }
    }
    @Override
    public int compareTo(Actividad another) {
        if (this.fechaI.compareToIgnoreCase(another.fechaI) >0 ) {
            return 1;
        } else if (this.fechaI.compareToIgnoreCase(another.fechaI) <0 ) {
            return -1;
        }
        return 0;
    }
}