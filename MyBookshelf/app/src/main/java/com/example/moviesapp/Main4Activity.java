package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {

    CheckBox idFavoritos;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        idFavoritos = (CheckBox) findViewById(R.id.idFavoritos);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        final String titulo = getIntent().getExtras().getString("tvTitulo");
        String ano = getIntent().getExtras().getString("tvAno");
        String genero = getIntent().getExtras().getString("tvGenero");
        String poster = getIntent().getExtras().getString("ivID");
        String avaliacao = getIntent().getExtras().getString("tvAvaliacao");
        String lancamento = getIntent().getExtras().getString("tvLancamento");
        String resumo = getIntent().getExtras().getString("tvResumo");
        String duracao = getIntent().getExtras().getString("tvDuracao");
        String diretor = getIntent().getExtras().getString("tvDiretor");
        String linguagem = getIntent().getExtras().getString("tvLinguagem");
        String pais = getIntent().getExtras().getString("tvPais");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.ctlID);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(titulo);

        final TextView tvTitulo = findViewById(R.id.m_tvTitulo);
        TextView tvAno = findViewById(R.id.m_tvAno);
        TextView tvGenero = findViewById(R.id.m_tvGenero);
        ImageView ivID = findViewById(R.id.m_ivID);
        TextView tvAvaliacao = findViewById(R.id.m_tvAvaliacao);
        TextView tvLancamento = findViewById(R.id.m_tvLancamento);
        TextView tvResumo = findViewById(R.id.aa_tvResumo);
        TextView tvDuaracao = findViewById(R.id.m_tvDuracao);
        TextView tvDiretor = findViewById(R.id.m_tvDiretor);
        TextView tvLinguagem = findViewById(R.id.m_tvLinguagem);
        TextView tvPais = findViewById(R.id.m_tvPais);


        tvTitulo.setText(titulo);
        tvAno.setText(ano);
        tvGenero.setText(genero);
        tvAvaliacao.setText(avaliacao);
        tvLancamento.setText(lancamento);
        tvResumo.setText(resumo);
        tvDuaracao.setText(duracao);
        tvDiretor.setText(diretor);
        tvLinguagem.setText(linguagem);
        tvPais.setText(pais);

        Glide.with(this).load(poster).into(ivID);

        idFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map <String, Object> mapFavoritos = new HashMap<>();
                mapFavoritos.put("favorito",idFavoritos.isChecked());

                if(idFavoritos.isChecked()){
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getEmail())
                            .collection("Filmes").document(titulo).update(mapFavoritos);
                }else{
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getEmail())
                            .collection("Filmes").document(titulo).update(mapFavoritos);
                }
            }
        });

    }


}
