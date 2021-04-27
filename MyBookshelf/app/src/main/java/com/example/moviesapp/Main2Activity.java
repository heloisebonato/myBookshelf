package com.example.moviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    EditText edEmail2;
    EditText edSenha2;
    Button bCadastro;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String emailUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edEmail2 = (EditText) findViewById(R.id.edEmail2);
        edSenha2 = (EditText) findViewById(R.id.edSenha2);
        bCadastro = (Button) findViewById(R.id.bCadastro);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

//        if(auth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        bCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edEmail2.getText().toString().trim();
                String senha = edSenha2.getText().toString().trim();

                auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Main2Activity.this,"Usuário cadastrado com sucesso!!!",Toast.LENGTH_SHORT).show();
                            emailUser = auth.getCurrentUser().getEmail();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(emailUser);
                            Map<String,Object> user = new HashMap<>();
                            user.put("email", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>(){
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG","Criado:" + emailUser);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","ERRO:" + e.toString());
                                }
                            });
                            Intent i = new Intent(getApplicationContext(), Main3Activity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(Main2Activity.this,"ERRO!!!"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }

//    private void criarUser(String email, String senha){
//        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(Main2Activity.this,"Usuário cadastrado com sucesso!!!",Toast.LENGTH_SHORT).show();
//                    userId = auth.getCurrentUser().getUid();
//                    DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
//                    Map<String,Object> user = new HashMap<>();
//                    user.put("email", edEmail2);
//                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d("TAG","Criado" + userId);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.d("TAG","ERRO:" + e.toString());
//                        }
//                    });
////                    Intent i = new Intent(Main2Activity.this,Main3Activity.class);
////                    startActivity(i);
//                    startActivity(new Intent(getApplicationContext(), Main3Activity.class));
//                    finish();
//                }else{
//                    Toast.makeText(Main2Activity.this,"ERRO!!!"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

//    protected void onStart(){
//        super.onStart();
//        auth = Conexao.getFirebaseAuth();
//    }
}
