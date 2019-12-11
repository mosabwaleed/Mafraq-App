package com.example.mafraqapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Citys_adapter extends RecyclerView.Adapter<Citys_adapter.MyViewHolder> {
    List<city> list ;
    Context context;

    public Citys_adapter(List<city> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.citys_card,parent,false));
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final city o = list.get(position);
        holder.name.setText(o.getName());
        Picasso.get().load(o.getImg()).into(holder.img);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, more_of_city.class)
                        .putExtra("desc",o.getDesc())
                        .putExtra("img",o.getImg())
                        .putExtra("name",o.getName())
                        .putExtra("lat",o.getLat())
                        .putExtra("lng",o.getLng()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView name;
        ImageView img;
        public MyViewHolder( View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
        }
    }
}