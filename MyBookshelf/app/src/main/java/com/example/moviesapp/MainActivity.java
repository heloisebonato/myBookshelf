package com.example.moviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edEmail;
    EditText edSenha;
    Button bEntrar;
    Button bCadastar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edSenha = (EditText) findViewById(R.id.edSenha);
        bEntrar = (Button) findViewById(R.id.bEntrar);
        bCadastar = (Button) findViewById(R.id.bCadastrar);
        eventoClick();
    }

    private void eventoClick(){
        bCadastar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });
        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString().trim();
                String senha = edSenha.getText().toString().trim();
                login(email, senha);
            }
        });
    }
    public void login(String email, String senha){
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(i);
                }else{
                    alert("email / senha errados");
                }
            }
        });
    }
    public void alert(String s){
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}