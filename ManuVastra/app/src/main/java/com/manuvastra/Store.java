package com.manuvastra;

import android.app.Activity;

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


/**
 * Created by sriram on 20/06/17.
 */


/**
 * An utility class for EasyShopping Application to capture all required data members in a single Object.
 *
 */
public class Store extends Activity {

    private static Store store;
    private static final String TAG = Store.class.getSimpleName();
    private List<Item> items = new ArrayList<>();
    private Map<String, String> departments = new HashMap<String, String>();

    public String getDepName() {
        return depName;
    }

    private void setDepName(String depName) {
        this.depName = depName;
    }

    private String depName;

    public Boolean getFeatured() {
        return featured;
    }

    private void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    private Boolean  featured;

    public static Store getStore(){
        if(store == null){
            store =  new Store();
        }
        return store;
    }

    private Store(){
        depName = "";
        featured = false;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }

    public void setDepartments(Map<String, String> departments){
        this.departments = departments;
    }


    //load file into items array list
    private void readItems(){


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
    }

    public List<Item> getProducts(String deptName, boolean featured) {
        String depId ="";
        for (String key : departments.keySet()){
            if(departments.get(key).equals(deptName)){
                depId = key;
                break;
            }
        }

        List<Item> itemsToDisplay = new ArrayList<Item>();
        if(!depId.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getDepId().equals(depId))
                    itemsToDisplay.add(items.get(i));
            }
        }
        else if(featured){
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isFeatured())
                    itemsToDisplay.add(items.get(i));
            }

        }else{
            itemsToDisplay = items;
        }

        setDepName(deptName);
        setFeatured(featured);
        return itemsToDisplay;
    }

    /**
     * Returns name for the corresponding depId
     * @param depId
     * @return String
     */
    public String getDepartmentName(String depId) {
        return departments.get(depId);
    }

    /**
     * Returns cxaAnalyticsObject
     * @return CxaAnalytics
     */

    public Map<String, String> getDepartments(){ return  departments; }
}
