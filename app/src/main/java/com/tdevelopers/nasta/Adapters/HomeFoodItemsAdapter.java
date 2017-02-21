package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.Query;
import com.tdevelopers.nasta.AllItemsActivity;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by saitej dandge on 20-02-2017.
 */

public class HomeFoodItemsAdapter extends FirebaseRecyclerAdapter<HomeFoodItemsAdapter.HomeFoodItemViewHolder, Dish> {
    Context context;

    public HomeFoodItemsAdapter(Query query, Class<Dish> itemClass) {
        super(query, itemClass);
    }

    @Override
    public HomeFoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.dish_tile, parent, false);
        this.context = parent.getContext();
        return new HomeFoodItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeFoodItemViewHolder holder, int position) {
        try {
            Dish current = getItem(position);
            if (current != null) {
                //Toast.makeText(context, current.pic+"", Toast.LENGTH_SHORT).show();
                //   Picasso.with(context).load(current.pic).into(holder.pic);
                holder.name.setText(current.name);
                Glide.with(context).load(current.pic).into(holder.pic);

            }
        } catch (Exception e) {

        } catch (Error e) {

        }
    }


    @Override
    protected void itemAdded(Dish item, String key, int position) {

    }

    @Override
    protected void itemChanged(Dish oldItem, Dish newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Dish item, String key, int position) {

    }

    @Override
    protected void itemMoved(Dish item, String key, int oldPosition, int newPosition) {

    }

    public class HomeFoodItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView pic;
        TextView name;
        View itemView;

        public HomeFoodItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            pic = (CircleImageView) itemView.findViewById(R.id.pic);
            name = (TextView) itemView.findViewById(R.id.name);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            view.getContext().startActivity(new Intent(view.getContext(), AllItemsActivity.class));


        }
    }

}
