package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.entity.Spirits;
import com.example.android.tools.DatabaseHandler;
import com.example.android.tools.SpiritsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
//        SQLiteDatabase sqlite_db = db.getWritableDatabase();
//        db.onUpgrade(sqlite_db, 1, 0);

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Spirits> spirits_list = db.getAllSpirits();

        Context mContext = MainActivity.this;
        SpiritsAdapter mAdapter = new SpiritsAdapter(spirits_list, mContext);
        ListView listSpirits = (ListView) findViewById(R.id.spirits_list);
        listSpirits.setAdapter(mAdapter);

        listSpirits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spirits spirits = (Spirits)listSpirits.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                intent.putExtra("spirits_id", spirits.get_id());
                intent.putExtra("spirits_name", spirits.get_name());
                intent.putExtra("spirits_description", spirits.get_description());
                startActivity(intent);
            }
        });
    }
    public void addSpirits(View view) {
        Intent intent = new Intent(this, SpiritsAddActivity.class);
        intent.putExtra("id", -1);
        startActivity(intent);
    }
}