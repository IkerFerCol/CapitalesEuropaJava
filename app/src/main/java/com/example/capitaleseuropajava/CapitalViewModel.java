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
        // Inicializa tu base de datos y DAO aquí
        AppDatabase appDatabase = AppDatabase.getDatabase(application); // Asegúrate de que este método esté disponible
        capitalDao = appDatabase.getCapitalDao();
    }

    public LiveData<List<Capital>> getCapitals() {
        return capitalDao.getCapital();
    }

    public void reload() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    getApplication().getApplicationContext()
            );

            CapitalAPI api = new CapitalAPI();
            ArrayList<Capital> result = api.buscar();
            capitalDao.deleteCapitales();
            capitalDao.addCapitals(result);
            return null;
        }
    }
}