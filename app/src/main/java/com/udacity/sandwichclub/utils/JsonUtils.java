package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich result = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            result = new Sandwich();
            result.setMainName(getString(jsonObject.getJSONObject("name"), "mainName"));

            result.setAlsoKnownAs(getList(jsonObject.getJSONObject("name"), "alsoKnownAs"));

            result.setPlaceOfOrigin(getString(jsonObject, "placeOfOrigin"));
            result.setDescription(getString(jsonObject, "description"));
            result.setImage(getString(jsonObject, "image"));

            result.setIngredients(getList(jsonObject, "ingredients"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    private static String getString(JSONObject jsonObject, String key) {
        String result = "";

        try {
            result = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static List<String> getList(JSONObject jsonObject, String key) {

        List<String> result = new ArrayList<String>();
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray(key);
            for(int i = 0; i < jsonArray.length(); i++) {
                result.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
