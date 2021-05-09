package com.example.android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.entity.Spirits;
import com.example.android.tools.DatabaseHandler;
import com.example.android.tools.FileHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class SpiritsAddActivity extends AppCompatActivity {
    private int id;
    private Bitmap selectedImage = null;
    private final FileHelper fileHelper = new FileHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spirits_add);
        Button image_button = findViewById(R.id.image_button);
        Button submit_button = findViewById(R.id.submit_new_spirits);
        EditText spirits_name = findViewById(R.id.new_spirits_name);
        EditText spirits_description = findViewById(R.id.new_spirits_description);
        DatabaseHandler db = new DatabaseHandler(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        if(id != -1) {
            Spirits spirits = db.getSpirits(id);
            spirits_name.setText(spirits.get_name());
            spirits_description.setText(spirits.get_description());
            submit_button.setText("更新白酒信息");
            selectedImage = fileHelper.loadImageBitmap(SpiritsAddActivity.this, id+"");
        }

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(id == -1) {
                        id = db.addSpirits(new Spirits(
                                spirits_name.getText().toString(),
                                spirits_description.getText().toString()));


                        Log.d("添加的id为", id + "");
                    } else {
                        db.updateSpirits(new Spirits(
                                id,
                                spirits_name.getText().toString(),
                                spirits_description.getText().toString())
                        );
                    }
                    fileHelper.saveImage(SpiritsAddActivity.this, selectedImage, id + "");
                    Intent refresh = new Intent(SpiritsAddActivity.this, MainActivity.class);
                    startActivity(refresh);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
                final Uri imageUri = data.getData();
            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}