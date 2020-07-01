package com.example.listadeitens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class NewItemActivity extends AppCompatActivity {

    final int GALLERY_REQUEST = 1;
    Uri selectedPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etTitle = (EditText) findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();

                EditText etDescription = (EditText) findViewById(R.id.etDescription);
                String description = etDescription.getText().toString();

                //armazenando os resultado que serão retornados
                Intent i = new Intent();
                i.setData(selectedPhoto);
                i.putExtra("title",title);
                i.putExtra("description",description);

                setResult(Activity.RESULT_OK,i);
                finish();

            }
        });

        ImageButton imbGallery = (ImageButton) findViewById(R.id.imbGallery);
        imbGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(NewItemActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent,GALLERY_REQUEST);

                }else{
                    ActivityCompat.requestPermissions(NewItemActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_REQUEST);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grandResults){
        if(requestCode == GALLERY_REQUEST){
            if(grandResults.length > 0 && grandResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent,GALLERY_REQUEST);
            }else{
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                selectedPhoto = data.getData();
                ImageView imvPreview = (ImageView) findViewById(R.id.imvpreview);
                imvPreview.setImageURI(selectedPhoto);
            }


        }
    }
}
