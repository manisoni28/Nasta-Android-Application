package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.R;

import java.util.ArrayList;

/**
 * Created by saitej dandge on 26-12-2016.
 */

public class DishDetailedAdapter extends RecyclerView.Adapter<DishDetailedAdapter.DishDetailedViewHolder> {

    ArrayList<Dish> data;
    Context context;

    public DishDetailedAdapter(ArrayList<Dish> data)

    {

        this.data = data;
    }


    @Override
    public DishDetailedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.dish_detailed_tile, parent, false);
        this.context = parent.getContext();
        return new DishDetailedAdapter.DishDetailedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DishDetailedViewHolder holder, int position) {
        Dish current = data.get(position);
        if (current != null) {

            Glide.with(context).load(current.pic).into(holder.pic);
            holder.name.setText(current.name);
            holder.price.setText(current.price + "");
            holder.rating.setText(current.rating + "");
            holder.time.setText("45 min");


        }

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class DishDetailedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, price, rating, time, tag;
        ImageView pic, add;

        DishDetailedViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            add = (ImageView) itemView.findViewById(R.id.add);
            price = (TextView) itemView.findViewById(R.id.price);
            tag = (TextView) itemView.findViewById(R.id.tag);


            rating = (TextView) itemView.findViewById(R.id.rating);

            time = (TextView) itemView.findViewById(R.id.time);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            add.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


        }
    }

}
