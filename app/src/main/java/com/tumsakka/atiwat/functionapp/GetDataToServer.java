package com.tumsakka.atiwat.functionapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by MiniBall on 2/25/2017.
 */

public class GetDataToServer extends AsyncTask<String, Void, String>{

    private Context context;

    public GetDataToServer(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(params[0]).build();
            Response response = okHttpClient.newCall(request).execute();
            String resJson = response.body().string();
            return resJson;


        } catch (Exception e) {
            Log.d("Login", "Okhttp ==>" + e.toString());
            return null;
        }



    }
}
