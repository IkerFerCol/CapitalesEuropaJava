package com.example.capitaleseuropajava;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        listacapitales = new ArrayList<>();
        setHasOptionsMenu(true);
        return binding.getRoot();



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        listacapitales = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Capital> capitales = CapitalAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Capital capital : capitales) {
                    listacapitales.add(capital);
                }
                adapter.notifyDataSetChanged();
            });
        });

        adapter = new CapitalAdapter(getContext(),
                R.layout.capital_list_item,
                listacapitales);
        binding.lvcapiales.setAdapter(adapter);


        binding.lvcapiales.setOnItemClickListener((adapter, fragment, i, l) -> {
            Capital capital = (Capital) adapter.getItemAtPosition(i);
            Toast.makeText(getContext(), "CLICK!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Click!");
            Bundle args = new Bundle();
            args.putSerializable("Capital", capital);

            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_capital_Details, args);
        }
    );

        model = new ViewModelProvider(this).get(CapitalViewModel.class);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_refresh) {
            Toast.makeText(getContext(), "Refrescado!", Toast.LENGTH_SHORT).show();
            Log.d("XXXMenu", "Refrescado");
        }

        if (id == R.id.action_settings) {
            Intent i = new Intent(getActivity(), SettingsActivity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void refresh() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Capital> capitales = CapitalAPI.buscar();

            listacapitales.clear();

            getActivity().runOnUiThread(() -> {
                for (Capital p : capitales) {
                    Log.d("XXX", p.toString());

                    listacapitales.add(p);
                }
                adapter.notifyDataSetChanged();
            });
        });


        binding.lvcapiales.setOnItemClickListener((adapterView, fragment, i, l) -> {
            Capital capital = adapter.getItem(i);
            Bundle args = new Bundle();
            args.putSerializable("Capital", capital);
            Log.d("XXX", capital.toString());
        });
    }
}