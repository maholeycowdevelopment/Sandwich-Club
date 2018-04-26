package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for parsing Json data
 */
public class JsonUtils {

    /**
     * Returns a Sandwich object to be rendered to the user
     * inside of DetailActivity. The json string comes from the
     * strings.xml file found in the res/values folder.
     * @param json an string with info about a user chosen sandwich
     * @return a Sanwich object
     */
    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonReader = new JSONObject(json);
            String mainName = jsonReader.getJSONObject("name").getString("mainName");
            List<String> alsoKnownAs = getListFromJsonArray(jsonReader.getJSONObject("name").getJSONArray("alsoKnownAs"));
            String placeOfOrigin = jsonReader.getString("placeOfOrigin");
            String description = jsonReader.getString("description");
            String image = jsonReader.getString("image");
            List<String> ingredients = getListFromJsonArray(jsonReader.getJSONArray("ingredients"));
            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Helper function for converting json array into a List of strings
     * @param jsonArray
     * @return a List of strings if successful, null otherwise
     */
    private static List<String> getListFromJsonArray(JSONArray jsonArray) {
        if(jsonArray == null) {
            return null;
        }

        List<String> list = new ArrayList<String>();

        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                String listItem = jsonArray.getString(i);
                list.add(listItem);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return list;
    }
}
