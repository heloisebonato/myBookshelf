package com.example.mybookshelf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.PermissionRequest;
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
    private final int GALERIA = 1;
    private final int PERMISSION_REQUEST = 2;

    ImageView imagem;
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
                Intent itent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(itent, GALERIA);
            }
        });

        //permite trazer as imagens da galeria
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        }
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
        //resposta em relação a escolha do usuario sobre a permissão de abrir galeria
        if (requestCode == PERMISSION_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //permissão dada
            }else{
                //permissão negada
            }
            return;
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
        if (resultCode == RESULT_OK && requestCode == GALERIA){
            Uri selectImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectImage, filePath, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePath[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap galeriaImagens = (BitmapFactory.decodeFile(picturePath));
            imagem.setImageBitmap(galeriaImagens);
        }
    }
}