package com.manuvastra;

/**
 * Created by sriram on 19/06/17.
 */

public class CartItem extends Item {
    private int quatity;

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public CartItem(Item item, int quatity){
        super(item);
        this.quatity = quatity;
    }
}
