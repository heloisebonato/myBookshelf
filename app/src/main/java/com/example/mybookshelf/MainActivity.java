package com.example.mybookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bCadastro = (Button) findViewById(R.id.bCadastro);
        Button bEntrar = (Button) findViewById(R.id.bEntrar);
        bCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), cadastro.class);
                startActivity(it);
            }
        });

        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textViewEmail = (TextView) MainActivity.this.findViewById(R.id.PlainTextEmail);
                TextView textViewSenha = (TextView) MainActivity.this.findViewById(R.id.PlainTextSenha);

                String email = textViewEmail.getText().toString();
                String senha = textViewSenha.getText().toString();

                for (int i = 0; i < UsuarioSingleton.getInstance().usuarioList.size(); i++) {
                    if (UsuarioSingleton.getInstance().usuarioList.get(i).getPlainTextEmail().equalsIgnoreCase(email) && UsuarioSingleton.getInstance().usuarioList.get(i).getPlainTextSenha().equals(senha)) {
                        Intent it = new Intent(v.getContext(), camera.class);
                        MainActivity.this.startActivity(it);
                    } else {
                        alerta("E-mail/Senha incorretos");
                    }
                }
            }
        });
    }
    protected void alerta (String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}