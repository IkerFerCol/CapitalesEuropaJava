package com.example.capitaleseuropajava;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.capitaleseuropajava.databinding.FragmentCapitalDetailsBinding;


public class Capital_Details extends Fragment {

    //Crear una instancia del fragmento, para crear el fragmento
    public static Capital_Details newInstance() {
        return new Capital_Details();
    }

    public Capital_Details() {
    }

    private FragmentCapitalDetailsBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCapitalDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //Infla el diseño del fragmento simplificando el acceso a los elementos
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        //Recuperar argumentos pasados al fragmento
        if (args != null) {
            //Recuperar el objeto Capital
            Capital capital = (Capital) args.getSerializable("Capital");
            if (capital != null) {
                Log.d("XXXDetail", capital.toString());
                showCapital(capital); //Llama al metodo showCapital() que muestra los datos en la interfaz
            }

        }

    }

    private void showCapital(Capital capital) {
        Log.d("CAPITAL", capital.toString());
        binding.capitalNameDetails.setText("Nombre: " + capital.getNombre());
        binding.capitalPobDetails.setText("Población: " + capital.getPoblacion().toString());
        binding.capitalCountDetails.setText("País: " + capital.getPais());
        Glide.with(getContext()).load(capital.getImg()).into(binding.imgCapitalSpriteDetails);
    }


}