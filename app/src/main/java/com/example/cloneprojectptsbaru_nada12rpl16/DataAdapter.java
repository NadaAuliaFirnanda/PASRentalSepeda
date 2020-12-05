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

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.RentalViewHolder> {


    private ArrayList<Model> datalist;

    public DataAdapter(ArrayList<Model>datalist){
        this.datalist = datalist;
    }

    @Override
    public RentalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
//        View view1 = layoutInflater.inflate(R.layout.list,parent,false);
//        return new
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RentalViewHolder holder,final int position) {

        holder.email.setText(datalist.get(position).getEmail());
        holder.nama.setText(datalist.get(position).getNama());
        holder.nohp.setText(datalist.get(position).getNohp());
        holder.alamat.setText(datalist.get(position).getAlamat());
        holder.noktp.setText(datalist.get(position).getNoktp());
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), EditCustomer.class);
                in.putExtra("id", datalist.get(position).getId());
                in.putExtra("nama", datalist.get(position).getNama());
                in.putExtra("email", datalist.get(position).getEmail());
                in.putExtra("nohp", datalist.get(position).getNohp());
                in.putExtra("alamat", datalist.get(position).getAlamat());
                in.putExtra("noktp", datalist.get(position).getNoktp());
                holder.itemView.getContext().startActivity(in);


            }
        });


    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() :0;
    }



    class RentalViewHolder extends RecyclerView.ViewHolder{
        private TextView email,nama,nohp,alamat,noktp;
        CardView card;


        RentalViewHolder(View itemView) {
            super(itemView);


            card = (CardView) itemView.findViewById(R.id.cardku);
            email = (TextView) itemView.findViewById(R.id.email);
            nama = (TextView) itemView.findViewById(R.id.nama);
            nohp = (TextView) itemView.findViewById(R.id.nohp);
            alamat = (TextView) itemView.findViewById(R.id.alamat);
            noktp = (TextView) itemView.findViewById(R.id.noktp);
        }
    }
}