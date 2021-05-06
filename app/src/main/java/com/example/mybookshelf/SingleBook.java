package com.example.mybookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleBook extends AppCompatActivity {

    private TextView title, opinion, category;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_book);

        title = (TextView) findViewById(R.id.singlebook_titulo);
        category = (TextView) findViewById(R.id.singlebook_categoria);
        opinion = (TextView) findViewById(R.id.singlebook_opiniao);
        img = (ImageView) findViewById(R.id.id_image_book);

        //recebe data
        Intent intent = getIntent();
        String Titulo = intent.getExtras().getString("Titulo");
        String Categoria = intent.getExtras().getString("Categoria");
        String Opiniao = intent.getExtras().getString("Opiniao");
        int Imagem = intent.getExtras().getInt("Thumbnail");

        //enviando valores

        title.setText(Titulo);
        opinion.setText(Opiniao);
        category.setText(Categoria);
        img.setImageResource(Imagem);
    }
}