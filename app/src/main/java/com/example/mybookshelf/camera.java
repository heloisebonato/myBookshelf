package com.example.mybookshelf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class camera extends AppCompatActivity {


    static final int CAMERA_PERMISSION_CODE = 2001;
    static final int CAMERA_INTENT_CODE = 3001;

    ImageView imageViewCamera;

    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        imageViewCamera = findViewById(R.id.imageViewCamera);
        Button buttonGaleria = (Button) findViewById(R.id.buttonGaleria);
        buttonGaleria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), MainActivity.class);
                startActivity(it);
            }
        });
    }
    public void buttonCameraClicked(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestCameraPermission();
        } else {
            sendCameraIntent();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void requestCameraPermission(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if(checkSelfPermission(Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.CAMERA
                },CAMERA_PERMISSION_CODE);
            }else{
                sendCameraIntent();
            }
        }else{
            Toast.makeText(camera.this,
                    "No camera available",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendCameraIntent();
            }else{
                Toast.makeText(camera.this,
                        "Camera Permission Denied",Toast.LENGTH_LONG).show();
            }
        }
    }
    void sendCameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION,true);
        if(intent.resolveActivity(getPackageManager()) != null){
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(new Date());
            String picName = "pic_"+timeStamp;
            File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File pictureFile = null;
            try {
                pictureFile = File.createTempFile(picName,".jpg",dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(pictureFile != null){
                picturePath = pictureFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(
                        camera.this,
                        "com.example.mybookshelf.fileprovider",
                        pictureFile
                );
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intent,CAMERA_INTENT_CODE);

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_INTENT_CODE) {
            if (resultCode == RESULT_OK) {
                File file = new File(picturePath);
                if (file.exists()) {
                    imageViewCamera.setImageURI(Uri.fromFile(file));
                }
            } else {
                Toast.makeText(camera.this,
                        "Problem getting the image from the camera app",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}