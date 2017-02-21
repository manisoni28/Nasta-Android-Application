package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tdevelopers.nasta.ApiHelper;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.R;

/**
 * Created by saitej dandge on 25-12-2016.
 */

public class HotelItemsAdapter extends RecyclerView.Adapter {
    Dish[] data;
    Context context;
    int VIEW_TYPE_HEADER = 0;

    int VIEW_TYPE_NON_HEADER = 1;

    public HotelItemsAdapter(Dish[] data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NON_HEADER) {
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.dish_rv_item, parent, false);
            this.context = parent.getContext();
            return new HotelItemsAdapter.HotelItemsViewHolder(itemView);
        } else {

            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.item_header, parent, false);
            this.context = parent.getContext();
            return new HeaderViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_TYPE_HEADER;
        else
            return VIEW_TYPE_NON_HEADER;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder outholder, int position) {

        if (outholder instanceof HotelItemsViewHolder) {
            HotelItemsViewHolder holder = (HotelItemsViewHolder) outholder;
            Dish current = data[position - 1];
            if (current != null) {
                holder.name.setText(current.name);
                Glide.with(context).load(current.pic).into(holder.pic);
                holder.rating.setText(current.rating + "");
                holder.price.setText(current.price + "");
            }
        }


    }

    @Override
    public int getItemCount() {
        return data != null ? data.length + 1 : 0;
    }

    class HotelItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView name;
        ImageView pic;
        TextView rating;
        TextView price;
        ImageView addtocart;

        HotelItemsViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            price = (TextView) itemView.findViewById(R.id.price);
            rating = (TextView) itemView.findViewById(R.id.rating);
            addtocart = (ImageView) itemView.findViewById(R.id.addtocart);
            addtocart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Snackbar.make(view, "Added to cart", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            ApiHelper.currentUser.addToCart(data[getLayoutPosition()].id);

        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
