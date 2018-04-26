package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Utility class for parsing Json data
 */
public class JsonUtils {
//    private String mainName;
//    private List<String> alsoKnownAs = null;
//    private String placeOfOrigin;
//    private String description;
//    private String image;
//    private List<String> ingredients = null;
    /**
     * Returns a Sandwich object to be rendered to the user
     * inside of DetailActivity. The json string comes from the
     * strings.xml file found in the res/values folder.
     * @param json an string with info about a user chosen sandwich
     * @return
     */
    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonReader = new JSONObject(json);
            String mainName = jsonReader.getJSONObject("name").getString("mainName");
            List<String> alsoKnownAs = (List<String>) jsonReader.getJSONObject("name").getJSONArray("alsoKnowAs");
            String placeOfOrigin = jsonReader.getString("placeOfOrigin");
            String description = jsonReader.getString("description");
            String image = jsonReader.getString("image");
            List<String> ingredients = (List<String>) jsonReader.getJSONArray("ingredients");

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
