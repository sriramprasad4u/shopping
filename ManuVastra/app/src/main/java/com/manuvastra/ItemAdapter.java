package com.manuvastra;

import android.content.Context;
import android.content.Intent;
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
public class ItemAdapter extends ArrayAdapter<Item> {

    private int layoutResource;

    public ItemAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ItemAdapter(Context context, int layoutResource, List<Item> itemList) {
        super(context, layoutResource, itemList);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);

            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.image);
            holder.title = (TextView) view.findViewById(R.id.item_title);
            holder.rating = (RatingBar) view.findViewById(R.id.rating);
            holder.price = (TextView) view.findViewById(R.id.item_price);
            holder.addToCart = (TextView) view.findViewById(R.id.addToCart);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Item item = getItem(position);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cart.getCart().setProduct(item);

                Intent intent = new Intent(getContext(), ProductDescription.class);
                getContext().startActivity(intent);
            }

        });

        if (item != null) {
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

            if(holder.addToCart !=null){
                holder.addToCart.setText("ADD TO CART");
                holder.addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cart.getCart().addProductToCart(item, 1);
//                        Intent intent = new Intent(getContext(), DisplayCart.class);
//                        getContext().startActivity(intent);
                    }
                });

            }
        }
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView title;
        RatingBar rating;
        TextView price;
        TextView addToCart;
    }
}
