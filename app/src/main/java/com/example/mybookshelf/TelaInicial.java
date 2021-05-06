package com.example.mybookshelf;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

        List<Books> listBooks;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tela_inicial);

            listBooks = new ArrayList<>();
            listBooks.add(new Books("Quem é você alasca?", "Romance", "a história tem seus atrativos e uma narrativa que, mesmo com personagens nada carismáticos, envolve e suga completamente o leitor.", R.drawable.alasca));
            listBooks.add(new Books("Harry Potter e o Cálice de Fogo", "Fantasia", " uma das leituras mais agradáveis da série. A ação constante prende facilmente o leitor; a assustadora volta de Voldemort, por fim, evidencia que tempos sombrios estão a caminho", R.drawable.harrypotter));
            listBooks.add(new Books("O Nome do Vento", "Fantasia", "Ele foge do padrão e, na minha opinião, isso é excelente. Eu me identifiquei com ele!!!", R.drawable.nomedovento));
            listBooks.add(new Books("SCRUM: a arte de fazer o dobro do trabalho na metade do tempo", "", "Leitura rápida e interessante, vale a pena também para abrir a mente para uma nova maneira de gerenciar projetos mais rápido e melhor", R.drawable.scrum));

            RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView);
            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, listBooks);
            myrv.setLayoutManager(new GridLayoutManager(this, 3));
            myrv.setAdapter(myAdapter);
        }
}