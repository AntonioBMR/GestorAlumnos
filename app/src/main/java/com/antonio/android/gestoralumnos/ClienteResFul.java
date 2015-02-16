package com.antonio.android.gestoralumnos;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Antonio on 21/01/2015.
 */
public class ClienteResFul {

    public static String delete(String url){
        HttpClient clienteHttp=new DefaultHttpClient();
        HttpDelete delete= new HttpDelete(url);
        delete.setHeader("content-type","aplication/json");
        try{
            HttpResponse respuestaHttp = clienteHttp.execute(delete);
            String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
            return respuesta;
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    public static String get(String url){
        HttpClient clienteHttp=new DefaultHttpClient();
        HttpGet get= new HttpGet(url);
        get.setHeader("content-type", "application/json");
        try{
            HttpResponse respuestaHttp = clienteHttp.execute(get);
            String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
            return respuesta;
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    public static String post(String url, JSONObject js){
        HttpClient clienteHttp = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("content-type", "application/json");
        try{

            StringEntity entidad = new StringEntity(js.toString());
            post.setEntity(entidad);
            HttpResponse respuestaHttp = clienteHttp.execute(post);
            String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
            return respuesta;
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error ";
    }
    public static String put(String url, JSONObject js){
        HttpClient clienteHttp = new DefaultHttpClient();
        HttpPut put = new HttpPut(url);
        put.setHeader("content-type", "application/json");
        JSONObject objetoJSON = js;

        try{
           /* objetoJSON.put("cadena", "valor");
            objetoJSON.put("numero", 1);*/
            StringEntity entidad = new StringEntity(objetoJSON.toString());
            put.setEntity(entidad);
            HttpResponse respuestaHttp = clienteHttp.execute(put);
            String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
            return respuesta;
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (JSONException e) {
            e.printStackTrace();
        }*/
        return "error";
    }
}
