package com.example.capitaleseuropajava;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CapitalAPI {
    private final String BASE_URL = "https://mblxirdgpahxhshiizqj.supabase.co/rest/v1/CapitalsofEurope?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1ibHhpcmRncGFoeGhzaGlpenFqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjgyNjEsImV4cCI6MjA0NzI0NDI2MX0.BmebycsUJvj-zRtEfU65fHkhiKbth7IhAe5QJeYLWQI";

    String getNames(String name) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("capital")
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    public static ArrayList<Capital> buscar() {
        ArrayList<Capital> capitales = new ArrayList<>();
        try {

            String response = HttpUtils.get("https://mblxirdgpahxhshiizqj.supabase.co/rest/v1/CapitalsofEurope?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1ibHhpcmRncGFoeGhzaGlpenFqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjgyNjEsImV4cCI6MjA0NzI0NDI2MX0.BmebycsUJvj-zRtEfU65fHkhiKbth7IhAe5QJeYLWQI");
            //JSONObject jsonObject = new JSONObject(response);
            //JSONArray results = jsonObject.getJSONArray("results");
            JSONArray resultado = new JSONArray(response);


            for (int i = 0; i < resultado.length(); i++) {
                JSONObject capitalObj = resultado.getJSONObject(i);
                String name = capitalObj.getString("Nombre");
                Log.d("XXX", name);
                String iamgen = capitalObj.getString("Img");
                int id = capitalObj.getInt("id");
                int poblacion = capitalObj.getInt("Poblacion");

                Capital capital = new Capital(id, name, iamgen, poblacion);
                capitales.add(capital);

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return capitales;
    }



    private String doCall(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            Log.d("XXX", JsonResponse);
            return JsonResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
