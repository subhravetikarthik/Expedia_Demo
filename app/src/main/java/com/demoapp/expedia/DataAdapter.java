package com.demoapp.expedia;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Hotel> listings;
    private Context appContext;

    public DataAdapter(List<Hotel> list, Context context) {
        this.listings = list;
        this.appContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.listingTitle.setText(listings.get(position).getHotelName());
        String imageUri = listings.get(position).getHotelImageURL();
        Picasso.with(appContext).load(imageUri).into(holder.listingImage);
        holder.listingPrice.setText(listings.get(position).getPrice());
        String rating = String.valueOf(listings.get(position).getGuestRating())
                .concat(appContext.getString(R.string.guestRatingText));
        holder.guestRating.setText(rating);
    }

    @Override
    public int getItemCount() {
        return listings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView listingImage;
        private TextView listingTitle;
        private TextView listingPrice;
        private TextView guestRating;

        public ViewHolder(View view) {
            super(view);
            listingImage = view.findViewById(R.id.imageView);
            listingTitle = view.findViewById(R.id.hotelName);
            guestRating = view.findViewById(R.id.guestRating);
            listingPrice = view.findViewById(R.id.hotelPrice);
        }
    }

}
