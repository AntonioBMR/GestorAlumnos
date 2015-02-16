package com.antonio.android.gestoralumnos;

import android.os.AsyncTask;

/**
 * Created by Antonio on 14/02/2015.
 */
public class GestorDelete extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String[] params) {
        String r = ClienteResFul.delete(params[0]);
        return r;
    }


    @Override
    protected void onPostExecute(String r) {
        super.onPostExecute(r);
    }
}


