package com.chnulabs.androidApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class DishActivity extends AppCompatActivity {

    public static final String KIND_OF_DISH = "kindOfDish";

    private float textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        Intent intent = getIntent();
        String kindOfDish = intent.getStringExtra(KIND_OF_DISH);

        String txtDish = "";
        for (Dish s : Dish.getDishes(kindOfDish)) {
            txtDish += s.getName() + "  " + s.getPortionWeightByGrams() + "\n";
        }
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(txtDish);
        textSize = textView.getTextSize();
        if (savedInstanceState != null) {
            textSize = savedInstanceState.getFloat("textSize");
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
    }

    public void onBtnSendClick(View view) {
        TextView textView = (TextView) findViewById(R.id.text);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "Список страв");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText());
        startActivity(intent);
    }

    public void onBtnPlusClick(View view) {
        textSize *= 1.1f;
        TextView textView = findViewById(R.id.text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("textSize", textSize);
    }
}