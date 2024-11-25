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

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Capital_Details#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Capital_Details extends Fragment {


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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        if (args != null) {
            Capital capital = (Capital) args.getSerializable("Capital");
            if (capital != null) {
                Log.d("XXXDetail", capital.toString());
                showCapital(capital);
            }

        }

    }

    private void showCapital(Capital capital) {
        Log.d("CAPITAL", capital.toString());
        binding.capitalNameDetails.setText("Nombre: " + capital.getNombre());
        binding.capitalPobDetails.setText("Población: " + capital.getPoblacion().toString());
        Glide.with(getContext()).load(capital.getImg()).into(binding.imgPokemonSpriteDetails);
    }

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public Capital_Details() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Capital_Details.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Capital_Details newInstance(String param1, String param2) {
//        Capital_Details fragment = new Capital_Details();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_capital__details, container, false);
//    }
}