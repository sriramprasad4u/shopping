package com.manuvastra;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by weizeng on 3/18/2016.
 */
public class CartAdapter extends ArrayAdapter<CartItem> {

    private int layoutResource;
    public CartAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CartAdapter(Context context, int layoutResource, List<CartItem> cartItems) {
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
            holder.numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
            holder.remove = (TextView) view.findViewById(R.id.remove);
            view.setTag(holder);
        } else {
            holder = (CartHolder) view.getTag();
        }

        final CartItem item = getItem(position);

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

            if(holder.numberPicker != null) {
                holder.numberPicker.setMinValue(1);
                //Specify the maximum value/number of NumberPicker
                holder.numberPicker.setMaxValue(10);

                holder.numberPicker.setValue(item.getQuatity());
                //Set a value change listener for NumberPicker
                holder.numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        //Display the newly selected number from picker
                        item.setQuatity(newVal);
                        Cart.getCart().putProductToCart(item);
                    }
                });
            }
            if(holder.remove != null) {
                holder.remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cart.getCart().removeItem(item.getId());
                        Intent intent = new Intent(getContext(), DisplayCart.class);
                        getContext().startActivity(intent);
                    }
                });
            }
        }
        return view;
    }

    static class CartHolder {
        ImageView imageView;
        TextView title;
        RatingBar rating;
        TextView price;
        NumberPicker numberPicker;
        TextView remove;
    }
}
