package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.R;

/**
 * Created by saitej dandge on 29-12-2016.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartViewHolder> {
    private Context context;

    private Dish[] data;

    public CartItemAdapter(Dish[] data) {
        this.data = data;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.cart_tile, parent, false);
        this.context = parent.getContext();
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        Dish current = data[position];
        if (current != null) {
            int counter = Integer.parseInt(holder.count.getText().toString());
            holder.name.setText(current.name);

            holder.finalprice.setText((current.price) * counter + "");


            Glide.with(context).load(current.pic).into(holder.pic);

        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.length : 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pic;
        TextView name, count, finalprice;
        Button plus, minus;

        CartViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            name = (TextView) itemView.findViewById(R.id.name);
            count = (TextView) itemView.findViewById(R.id.count);
            finalprice = (TextView) itemView.findViewById(R.id.finalprice);
            plus = (Button) itemView.findViewById(R.id.plus);
            minus = (Button) itemView.findViewById(R.id.minus);
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int counter = Integer.parseInt(this.count.getText().toString());

            int id = view.getId();
            switch (id) {
                case R.id.plus:

                    if (counter < 10)
                        counter++;


                    break;

                case R.id.minus:
                    if (counter > 1)
                        counter--;
                    break;
            }

            this.finalprice.setText((data[getLayoutPosition()].price) * counter + "");

            this.count.setText(counter + "");
        }
    }
}
