package com.chnulabs.androidApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class InfoListActivity extends AppCompatActivity {

    public static final String KIND_OF_DISH = "kindOfDish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        ArrayAdapter<Info> billArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getDataFromDB()
        );

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = ((Info) adapterView.getItemAtPosition(i)).getId();
                Intent intent = new Intent(InfoListActivity.this, InfoActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        };
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(billArrayAdapter);
        listView.setOnItemClickListener(listener);

        getDataFromDB();
    }

    public void AddClick(View view) {
        Intent intent = new Intent(InfoListActivity.this,
                AddKindOfDishActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kind_of_dish_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "test message");
        shareActionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_dish) {
            startActivity(
                    new Intent(this, AddKindOfDishActivity.class)
            );
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Info> getDataFromDB() {
        ArrayList<Info> info = new ArrayList<Info>();

        SQLiteOpenHelper sqLiteHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            Cursor cursor = db.query("Dish",
                    new String[]{"id", "kindOfDish", "nameOfRestaurant", "cashExistsFlg", "terminalExistsFlg"},
                    null, null, null, null, "kindOfDish");
            while (cursor.moveToNext()) {
                info.add(
                        new Info(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                (cursor.getInt(3) > 0),
                                (cursor.getInt(4) > 0))
                );
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG);
            toast.show();
        }
        return info;
    }
}