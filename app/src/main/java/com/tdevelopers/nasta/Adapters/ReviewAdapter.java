package com.tdevelopers.nasta.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tdevelopers.nasta.Entities.Review;
import com.tdevelopers.nasta.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by saitej dandge on 28-12-2016.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Review[] data;
    Context context;

    public ReviewAdapter(Review[] data) {
        this.data = data;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.review_tile, parent, false);
        this.context = parent.getContext();
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {


        Review current = data[position];
        if (current != null) {
            holder.name.setText(current.name);
            holder.comment.setText(current.comment);

            holder.rating.setText(current.rating + "");
            Glide.with(context).load(current.pic).into(holder.pic);
        }

    }


    @Override
    public int getItemCount() {
        return data != null ? data.length : 0;
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {


        TextView name;
        CircleImageView pic;
        TextView comment;
        TextView rating;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);

            comment = (TextView) itemView.findViewById(R.id.comment);
            rating = (TextView) itemView.findViewById(R.id.rating);

            pic = (CircleImageView) itemView.findViewById(R.id.pic);
        }
    }
}
