package com.example.cloneprojectptsbaru_nada12rpl16;

import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class SepedaAdapter extends RecyclerView.Adapter<SepedaAdapter.RentalViewHolder> {


    private ArrayList<ModelMasterBarang> datalist;

    public SepedaAdapter(ArrayList<ModelMasterBarang>datalist){
        this.datalist = datalist;
    }

    @Override
    public RentalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list,parent,false);
//        View view1 = layoutInflater.inflate(R.layout.list,parent,false);
//        return new
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RentalViewHolder holder,final int position) {

        holder.KodeSepeda.setText(datalist.get(position).getKodesepeda());
        holder.Merk.setText(datalist.get(position).getMerk());
        holder.Warna.setText(datalist.get(position).getWarna());
        holder.HargaSewa.setText(datalist.get(position).getHargasewa());
        holder.Jenis.setText(datalist.get(position).getJenis());
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), EditSepeda.class);
                in.putExtra("id", datalist.get(position).getId());
                in.putExtra("kodesepeda", datalist.get(position).getKodesepeda());
                in.putExtra("merk", datalist.get(position).getMerk());
                in.putExtra("warna", datalist.get(position).getWarna());
                in.putExtra("hargasewa", datalist.get(position).getHargasewa());
                in.putExtra("jenis", datalist.get(position).getJenis());
                holder.itemView.getContext().startActivity(in);


            }
        });


    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() :0;
    }


    class RentalViewHolder extends RecyclerView.ViewHolder{
        private TextView KodeSepeda,Merk,Warna,HargaSewa,Jenis;
        CardView card;


        RentalViewHolder(View itemView) {
            super(itemView);


            card = (CardView) itemView.findViewById(R.id.cardku);
            KodeSepeda = (TextView) itemView.findViewById(R.id.kodesepeda);
            Merk = (TextView) itemView.findViewById(R.id.merk);
            Warna = (TextView) itemView.findViewById(R.id.warna);
            HargaSewa = (TextView) itemView.findViewById(R.id.hargasewa);
            Jenis = (TextView) itemView.findViewById(R.id.jenis);
        }
    }
}