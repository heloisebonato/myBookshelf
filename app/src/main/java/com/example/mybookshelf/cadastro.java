package com.example.mybookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        Button bCriar = (Button) findViewById(R.id.bCadastrar);
        bCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView TexteViewEmail = (TextView) cadastro.this.findViewById(R.id.PlainTextLogin);
                TextView TextViewSenha = (TextView) cadastro.this.findViewById(R.id.PlainTextSenha);

                String email = TexteViewEmail.getText().toString();
                String senha = TextViewSenha.getText().toString();

                Usuario usuario = new Usuario();
                usuario.setPlainTextEmail(email);
                usuario.setPlainTextSenha(senha);

                UsuarioSingleton.getInstance().usuarioList.add(usuario);

                Intent it = new Intent(v.getContext(), TelaInicial.class);
                startActivity(it);
            }
        });
    }
}

