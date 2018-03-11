package com.manuvastra;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sriram on 19/06/17.
 */

/**
 * Cart class holds information about products in the cart to be placed
 */
public class Cart {

    private static Cart cart;
    private double price;
    Map<String, CartItem> cartItems;
    private Item product;
    private Map<String,String> departments;

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    TextView textView;

    public static Cart getCart(){
        if(cart == null){
            cart = new Cart();
        }

        return cart;
    }

    public void setDepartments(Map<String,String> departments){
        this.departments = departments;
    }

    public Map<String,String> getDepartments(){
        if(this.departments == null){
            departments = new HashMap<String, String>();
        }

        return  departments;
    }

    public double getPrice() {
        calculatePrice();
        return price;
    }

    private void calculatePrice() {
        price = 0;
        if(cartItems!=null) {
            for (String key : cartItems.keySet()) {
                price += cartItems.get(key).getPrice() * cartItems.get(key).getQuatity();
            }
        }
    }

    /**
     * Adds product to the cart
     * @param item
     * @param quantity
     */
    public void addProductToCart(Item item, int quantity){
        if(cartItems == null){
            cartItems = new HashMap<String, CartItem>();
        }

        if(cartItems.containsKey(item.getId())){
            int i = cartItems.get(item.getId()).getQuatity();
            cartItems.get(item.getId()).setQuatity(i+quantity);
        }else{

            cartItems.put(item.getId(), new CartItem(item, quantity));
        }

        textView.setText(""+getPrice());
    }

    /**
     * replaces product in the cart
     * @param item
     */
    public void putProductToCart(CartItem item){
        if(cartItems == null){
            cartItems = new HashMap<String, CartItem>();
        }
        cartItems.put(item.getId(), item);
        textView.setText(""+getPrice());
    }

    /**
     * Returns products in the cart as a Map
     * @return
     */
    public Map<String, CartItem> getCartItems(){
        if(cartItems == null){
            cartItems = new HashMap<String, CartItem>();
        }

        return cartItems;
    }

    /**
     * Sets product which is selected for description
     * @param product
     */
    public void setProduct(Item product) {
        this.product = product;
    }

    /**
     * Removes product from cart
     * @param id
     */
    public void removeItem(String id) {
        cartItems.remove(id);
    }

    public List<CartItem> getCartItemsList() {
        List<CartItem> cartItems = new ArrayList<CartItem>();

        for (String key : Cart.getCart().getCartItems().keySet()){
                cartItems.add(Cart.getCart().getCartItems().get(key));
        }

        return cartItems;
    }

    public int getCount() {
        if(cartItems!=null)
            return cartItems.size();


        return 0;
    }

    /**
     * Get the recent pro
     * @return
     */
    public Item getProduct() {
        return product;
    }


    /**
     * Clears the cart
     */
    public void clearCart(){
        cartItems = new HashMap<String,CartItem>();
    }

    public void showDialog(final Context context, final boolean navigate) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("You don't have any items in cart. please add to place order");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if(navigate) {
                                    Intent intent = new Intent(context, BrowseActivity.class);
                                    context.startActivity(intent);
                                }
                            }
                        });
        alertDialogBuilder.create().show();
    }
}
