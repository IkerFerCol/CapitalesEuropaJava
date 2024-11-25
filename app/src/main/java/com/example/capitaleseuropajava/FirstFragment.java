package com.example.capitaleseuropajava;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.capitaleseuropajava.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList<Capital> listacapitales;
    ArrayAdapter<Capital> adapter;

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
            ArrayList<Capital> pokemons = CapitalAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Capital p : pokemons) {
                    listacapitales.add(p);
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
            Bundle args = new Bundle();
            args.putSerializable("Capital", capital);

            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_capital_Details, args);
        }
    );
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh){
            refresh();
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
            ArrayList<Capital> pokemons = CapitalAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Capital p : pokemons) {
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