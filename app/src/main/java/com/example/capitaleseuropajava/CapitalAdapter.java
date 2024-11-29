package com.example.capitaleseuropajava;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class CapitalAdapter extends ArrayAdapter<Capital> {


    public CapitalAdapter(@NonNull Context context, int resource, @NonNull List<Capital> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Capital capital = getItem(position);

        //Si es nulo, infla el diseño del elemento, sirve para reciclar vistas y mejorar el rendimiento
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.capital_list_item, parent, false);
        }

        //Referencias a los elementos del layout
        TextView txtNombre = convertView.findViewById(R.id.txtcapitalLista);
        ImageView imgImagen = convertView.findViewById(R.id.imgcapitallist);

        txtNombre.setText(capital.getNombre());
        Glide.with(getContext()).load(capital.getImg()).into(imgImagen);

        //Devuelve la vista
        return convertView;
    }
}
