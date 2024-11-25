package com.example.capitaleseuropajava;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class CapitalViewModel extends AndroidViewModel {

    private final AppDatabase appDatabase;
    private final CapitalDao capitalDao;
    private LiveData<List<Capital>> capitales;

    public CapitalViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(application);
        capitalDao = appDatabase.getCapitalDao();
        capitales = capitalDao.getCapital();
    }

    public LiveData<List<Capital>> getCapitales() {
        return capitales;
    }

    public void reload() {
        new RefreshDataTask().execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());

            String tipoConsulta = preferences.getString("tipo_consulta", "vistas");

            CapitalAPI api = new CapitalAPI();
            ArrayList<Capital> result = api.buscar();

            capitalDao.deleteCapitales();
            capitalDao.addCapitals(result);

            return null;
        }
    }
}