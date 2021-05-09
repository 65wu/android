package com.example.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tools.DatabaseHandler;
import com.example.android.tools.FileHelper;

public class ContentActivity extends AppCompatActivity {
    int spirits_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        spirits_id = intent.getIntExtra("spirits_id", -1);
        String spirits_name = intent.getStringExtra("spirits_name");
        String spirits_description = intent.getStringExtra("spirits_description");


        TextView textViewName = findViewById(R.id.detailed_name);
        textViewName.setText(spirits_name);

        TextView textViewDescription = findViewById(R.id.detailed_description);
        textViewDescription.setText(spirits_description);

        ImageView img_icon = (ImageView)findViewById(R.id.detailed_icon);
        img_icon.setImageBitmap(new FileHelper().loadImageBitmap(
                this, spirits_id + ""));
    }

    public void deleteSpirits(View view) {
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteSpirits(spirits_id);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

    public void updateSpirits(View view) {
        Intent intent = new Intent(this, SpiritsAddActivity.class);
        intent.putExtra("id", spirits_id);
        startActivity(intent);
    }
}