package com.chnulabs.androidApplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddKindOfDishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kind_of_dish);
    }

    public void onAddBtnClick(View view) {
        EditText restaurant = (EditText) findViewById(R.id.addRestaurant);
        EditText kindOfDish = (EditText) findViewById(R.id.addKindOfDish);
        Info.addDish(
                new Info(restaurant.getText().toString(),
                        kindOfDish.getText().toString(),
                        false, false
                )
        );
        NavUtils.navigateUpFromSameTask(this);
    }
}