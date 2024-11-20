package com.example.capitaleseuropajava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.capitaleseuropajava.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList<String> listacapitales;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        listacapitales = new ArrayList<>();
        listacapitales.add("Madrid");
        listacapitales.add("Londres");
        listacapitales.add("Paris");

        adapter = new ArrayAdapter<>(getContext(),
                R.layout.capital_list_item,
                R.id.txtcapitallist,
                listacapitales);
        binding.lvcapiales.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}