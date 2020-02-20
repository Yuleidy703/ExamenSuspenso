package com.example.examensuspenso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorRevista extends ArrayAdapter<Revista> {
    public AdaptadorRevista(Context context, ArrayList<Revista> datos) {
        super(context, R.layout.ly_itemsrevistas, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_itemsrevistas, null);

        TextView lblVolumen = (TextView)item.findViewById(R.id.lblVolumen);
        lblVolumen.setText(getItem(position).getVolume());

        TextView lblnumero = (TextView)item.findViewById(R.id.lblNumero);
        lblnumero.setText(getItem(position).getNumber());


        TextView lblfecha = (TextView)item.findViewById(R.id.lblFechaPub);
        lblfecha.setText(getItem(position).getYear());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgPortada);
        Glide.with(this.getContext())
                .load(getItem(position).getPortada())
                .error(R.drawable.imgnotfound)
                .into(imageView);
        return(item);
    }
}
