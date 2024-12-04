package com.example.capitaleseuropajava;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CapitalAPI {

    //Metodo que devuelve la lista de los objetos Capital tras mirar la api
    public static ArrayList<Capital> buscar() {
        ArrayList<Capital> capitales = new ArrayList<>();
        try {
            //Realizar la petición HTTP utilizando la url con la ApiKey
            String response = HttpUtils.get("https://mblxirdgpahxhshiizqj.supabase.co/rest/v1/CapitalsofEurope?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1ibHhpcmRncGFoeGhzaGlpenFqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjgyNjEsImV4cCI6MjA0NzI0NDI2MX0.BmebycsUJvj-zRtEfU65fHkhiKbth7IhAe5QJeYLWQI");
            JSONArray resultado = new JSONArray(response);


            //Obtener los datos de los objetos
            for (int i = 0; i < resultado.length(); i++) {
                JSONObject capitalObj = resultado.getJSONObject(i);
                String name = capitalObj.getString("Nombre");
                Log.d("XXX", name);
                String iamgen = capitalObj.getString("Img");
                int id = capitalObj.getInt("id");
                int poblacion = capitalObj.getInt("Poblacion");
                String pais = capitalObj.getString("Pais");

                //Crear un objeto Capital con los datos obtenidos para luego añadir el objeto a la lista
                Capital capital = new Capital(id, name, iamgen, poblacion, pais);
                capitales.add(capital);

            }
        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
        return capitales;
    }


}
