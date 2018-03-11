package com.manuvastra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BrowseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = BrowseActivity.class.getSimpleName();
    private ItemAdapter adapter;

    //private Tracker tracker = TrackerUtil.getTracker(this);
    private boolean featured;
    private String depName;
    private TextView totalPrice;
    private ListView itemListView;
    private TextView type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        readItems();
        //navigation drawer set up
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navView.getMenu();
        SubMenu topChannelMenu = m.addSubMenu("General");

        topChannelMenu.add("All Products");
        topChannelMenu.add("Featured");

        topChannelMenu = m.addSubMenu("Departments");

        Map<String,String>  departments = Store.getStore().getDepartments();

        for (String key : departments.keySet()){
            topChannelMenu.add(departments.get(key));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        //connect list view to resources
        itemListView = (ListView) findViewById(R.id.item_list);
        totalPrice = (TextView) findViewById(R.id.total_price);
        Cart.getCart().setTextView(totalPrice);
        type = (TextView) findViewById(R.id.type);
        depName = Store.getStore().getDepName();
        featured = Store.getStore().getFeatured();
        displayProducts();
        setPrice();
    }

    private void displayProducts() {

        List<Item> itemsToDisplay = null;


        itemsToDisplay = Store.getStore().getProducts(depName, featured);

        if(itemsToDisplay != null) {
            adapter = new ItemAdapter(
                    this,
                    R.layout.browse_list_item,   // custom layout for the list
                    itemsToDisplay
            );

            itemListView.setAdapter(adapter);

            itemListView.setItemsCanFocus(true);

            adapter.notifyDataSetChanged();

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        getType();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart) {

            Intent intent = new Intent(getApplicationContext(), DisplayCart.class);
            getApplicationContext().startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String str = getType();
        if(item.getTitle().equals("Featured")){
            featured = true;
            depName = "";
        }
        else{
            featured = false;
            depName = item.getTitle().toString();
        }

        displayProducts();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void checkoutButtonClick(View view) {

        if(Cart.getCart().getCartItemsList().size()>0) {
            Intent intent = new Intent(this, ReviewCart.class);
            startActivity(intent);
        }
        else{
            Cart.getCart().showDialog(this, false);
        }
    }



    private String getType(){

        String str;
        if(!depName.isEmpty()) {
            str = depName;
        }
        else if(featured){
            str = "Featured";
        }else{
            str = "All Products";
        }

        type.setText(str);

        return str;
    }
    public void setPrice(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totalPrice.setText(Cart.getCart().getPrice()+"");
            }
        });
    }

    //load file into items array list
    private void readItems(){

        ArrayList<Item> items = new ArrayList<>();
        Map<String, String> departments = new HashMap<String, String>();
        String string = "";
        Scanner scan = new Scanner(getApplicationContext().getResources().openRawResource(R.raw.products));
        while (scan.hasNextLine()) {
            string += scan.nextLine();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray products = null;
        try {
            products = new JSONArray(string);
            for (int i = 0 ; i <products.length(); i++) {
                items.add(objectMapper.readValue(products.get(i).toString(),Item.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        string = "";

        scan = new Scanner(getResources().openRawResource(R.raw.departments));
        while (scan.hasNextLine()) {
            string += scan.nextLine();
        }

        JSONArray departs;
        try {
            Department department;
            departs = new JSONArray(string);
            for (int i = 0 ; i <departs.length(); i++) {
                department = objectMapper.readValue(departs.get(i).toString(),Department.class);
                departments.put(department.getId(),department.getTitle());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Store.getStore().setItems(items);
        Store.getStore().setDepartments(departments);
    }

}
