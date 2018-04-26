package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView mAlsoKnowAsTextView;
    TextView mIngredientsTextView;
    TextView mPlaceOfOriginTextView;
    TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        mAlsoKnowAsTextView = (TextView) findViewById(R.id.also_known_tv);
        mIngredientsTextView = (TextView) findViewById(R.id.ingredients_tv);
        mPlaceOfOriginTextView = (TextView) findViewById(R.id.origin_tv);
        mDescriptionTextView = (TextView) findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        if(sandwich.getAlsoKnownAs() != null || !sandwich.getAlsoKnownAs().isEmpty()) {
            for (String aka : sandwich.getAlsoKnownAs()) {
                mAlsoKnowAsTextView.append(aka + "\n");
            }
        } else {
            mAlsoKnowAsTextView.append("None\n");
        }

        if(sandwich.getIngredients() != null || !sandwich.getIngredients().isEmpty()) {
            for(String ingredient : sandwich.getIngredients()) {
                mIngredientsTextView.append(ingredient + "\n");
            }
        } else {
            mIngredientsTextView.append("Unavailable\n" );
        }

        mDescriptionTextView.append(sandwich.getDescription() + "\n");
        mPlaceOfOriginTextView.append(sandwich.getPlaceOfOrigin() + "\n");
    }
}
