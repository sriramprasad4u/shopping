package com.manuvastra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by weizeng on 3/18/2016.
 */
public class ReviewAdapter extends ArrayAdapter<CartItem> {

    private int layoutResource;

    public ReviewAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ReviewAdapter(Context context, int layoutResource, List<CartItem> cartItems) {
        super(context, layoutResource, cartItems);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final CartHolder holder;
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);

            holder = new CartHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.image);
            holder.title = (TextView) view.findViewById(R.id.item_title);
            holder.rating = (RatingBar) view.findViewById(R.id.rating);
            holder.price = (TextView) view.findViewById(R.id.item_price);
            holder.quatity = (TextView) view.findViewById(R.id.quantity);


            view.setTag(holder);
        } else {
            holder = (CartHolder) view.getTag();
        }

        final CartItem item = getItem(position);

        if (item != null) {
            view.findViewById(R.id.numberPicker).setVisibility(View.GONE);
            view.findViewById(R.id.remove).setVisibility(View.GONE);

            if(holder.imageView != null){
                holder.imageView.setImageResource(getContext().getResources().getIdentifier(item.getImage(), "drawable", getContext().getPackageName()));
            }

            if (holder.title != null) {
                holder.title.setText(item.getTitle());
            }

            if (holder.rating != null){
                holder.rating.setRating(item.getRating());
                holder.rating.setFocusable(false);
            }

            if (holder.price != null) {
                holder.price.setText("$ "+item.getPrice().toString());
            }

            if(holder.quatity != null){
                holder.quatity.setText("Quantity: "+item.getQuatity());
                holder.quatity.setVisibility(View.VISIBLE);
            }

        }
        return view;
    }

    static class CartHolder {
        ImageView imageView;
        TextView title;
        RatingBar rating;
        TextView price;
        TextView quatity;


    }
}
