package com.chnulabs.androidApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        Info info = null;
        SQLiteOpenHelper sqLiteHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            Cursor cursor = db.query("Dish",
                    new String[]{"id", "kindOfDish", "nameOfRestaurant", "cashExistsFlg", "terminalExistsFlg"},
                    "id=?", new String[]{Integer.toString(id)}, null, null, null);
            if (cursor.moveToFirst()) {
                info = new Info(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        (cursor.getInt(3) > 0),
                        (cursor.getInt(4) > 0)
                );
            } else {
                Toast toast = Toast.makeText(this, "Can`t find kind of dish with id " + id, Toast.LENGTH_SHORT);
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG);
            toast.show();
        }

        if (info != null) {

            EditText txtKindOfDish = (EditText) findViewById(R.id.kindOfDishEdit);
            txtKindOfDish.setText(info.getKindOfDish());
            TextView txtKind = (TextView) findViewById(R.id.textView2);
            txtKind.setText(info.getKindOfDish());

            EditText txtPriceOfDish = (EditText) findViewById(R.id.restaurantEdit);
            txtPriceOfDish.setText(info.getNameOfRestaurant());
            TextView txtPrice = (TextView) findViewById(R.id.textView3);
            txtPrice.setText(info.getNameOfRestaurant());

            ((CheckBox) findViewById(R.id.checkBox)).setChecked(
                    info.isCashExistsFlg()
            );

            ((CheckBox) findViewById(R.id.checkBox2)).setChecked(
                    info.isTerminalExistsFlg()
            );
        }
    }

    public void onBtnClick(View view) {

        String outString = "Ресторан: " + ((TextView) findViewById(R.id.restaurantEdit)).getText() + "\n";
        outString += "Вид страви: " + ((TextView) findViewById(R.id.kindOfDishEdit)).getText() + "\n";

        if (((RadioButton) findViewById(R.id.radioButton)).isChecked()) {
            outString += "Валюта - " + "UAH\n";
        }
        if (((RadioButton) findViewById(R.id.radioButton2)).isChecked()) {
            outString += "Валюта - " + "USD\n";
        }

        if (((CheckBox) findViewById(R.id.checkBox)).isChecked()) {
            outString += "Оплата - " + "готівка\n";
        }
        if (((CheckBox) findViewById(R.id.checkBox2)).isChecked()) {
            outString += "Оплата - " + "карта\n";
        }

        Toast toast = Toast.makeText(this, outString, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void onBtnDishListClick(View view) {
        Intent localIntent = getIntent();
        String kind = localIntent.getStringExtra(DishActivity.KIND_OF_DISH);

        Intent newIntent = new Intent(this, DishActivity.class);
        newIntent.putExtra(DishActivity.KIND_OF_DISH, kind);
        startActivity(newIntent);
    }
}