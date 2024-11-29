package com.example.capitaleseuropajava;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.capitaleseuropajava.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList<Capital> listacapitales;
    ArrayAdapter<Capital> adapter;
    CapitalViewModel model;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //Configuracion inicial del fragmento
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //Se inicia la lista y el adapter para el ListView
        listacapitales = new ArrayList<>();
        adapter = new CapitalAdapter(getContext(), R.layout.capital_list_item, listacapitales);
        binding.lvcapiales.setAdapter(adapter);
        binding.lvcapiales.setOnItemClickListener((adapterView, view2, i, l) -> {
            Capital capital = adapter.getItem(i);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Capital", capital);
            NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_capital_Details, bundle);
        });
        //asocia el viewmodel al fragmento
        model = new ViewModelProvider(this).get(CapitalViewModel.class);

        setHasOptionsMenu(true);
        return view;


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        //Observa los cambios en los datos y actualiza el adapter
        model.getCapitals().observe(getViewLifecycleOwner(), capitals -> {
            adapter.clear();
            adapter.addAll(capitals);
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_refresh) {
            Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
            Log.d("XXXMenu", "Click");
        }

        if (id == R.id.action_settings) {

            Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
            Log.d("XXXMenu", "Click");
            try {
                Intent i = new Intent(getActivity(), SettingsActivity.class);
                startActivity(i);
            } catch (Exception e) {
                Log.d("SettingsAct", "Error no va");
            }

            return true;
        } else if (id == R.id.action_refresh) {
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Metodo para recargar los datos desde la API
    void refresh() {
        model.reload();
    }
}