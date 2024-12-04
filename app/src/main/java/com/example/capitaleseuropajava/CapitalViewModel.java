package com.example.capitaleseuropajava;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class CapitalViewModel extends AndroidViewModel {
    private final CapitalDao capitalDao;

    public CapitalViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        capitalDao = appDatabase.getCapitalDao();
    }

    //Metodo para obtener la lista desde la bd
    public LiveData<List<Capital>> getCapitals() {
        return capitalDao.getCapital();
    }

    //Metodo para recargar los datos desde la api a la bd
    public void reload() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    //clase para realizar tareas en segundo plano (Descargar y guardar datos)
    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {


            CapitalAPI api = new CapitalAPI();
            ArrayList<Capital> result = api.buscar();
            capitalDao.deleteCapitales();
            capitalDao.addCapitals(result);
            return null;
        }
    }
}