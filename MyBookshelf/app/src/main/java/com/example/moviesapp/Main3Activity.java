package com.example.moviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    String url = "https://moelabs.org/filmes.json";

    Spinner spinnerGenresFilter;
    CheckBox checkBoxFilterFavorite;
    Button buttonFilter;

    JsonArrayRequest request;
    RequestQueue requestQueue;
    List<Movie> movieList;
    List<String> movieFilter;
    RecyclerView recyclerView;
    ArrayAdapter<String> adapter;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        spinnerGenresFilter = findViewById(R.id.lista);
        checkBoxFilterFavorite = findViewById(R.id.favoritos);
        buttonFilter = findViewById(R.id.listar);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvID);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        collectionReference = firebaseFirestore.collection("Filmes");

        jsonrequest();

        movieFilter = new ArrayList<>();
        movieFilter.add("Action");
        movieFilter.add("Adventure");
        movieFilter.add("Fantasy");
        movieFilter.add("Drama");
        movieFilter.add("Horror");
        movieFilter.add("Sci-fi");
        movieFilter.add("Thriller");
        movieFilter.add("Biography");
        movieFilter.add("Comedy");
        movieFilter.add("Crime");
        movieFilter.add("History");

        adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, movieFilter);
        spinnerGenresFilter.setAdapter(adapter);


        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrarFilmes(auth.getCurrentUser().getEmail(), checkBoxFilterFavorite.isChecked(), spinnerGenresFilter.getSelectedItem().toString());
            }
        });
    }

    private void jsonrequest(){
        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0 ; i < response.length(); i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(jsonObject.getString("Title"));
                        movie.setYear(jsonObject.getString("Year"));
                        movie.setGenre(jsonObject.getString("Genre"));
                        movie.setRated(jsonObject.getString("Rated"));
                        movie.setPoster(jsonObject.getString("Poster"));
                        movie.setReleased(jsonObject.getString("Released"));
                        movie.setPlot(jsonObject.getString("Plot"));
                        movie.setRuntime(jsonObject.getString("Runtime"));
                        movie.setDirector(jsonObject.getString("Director"));
                        movie.setLanguage(jsonObject.getString("Language"));
                        movie.setCountry(jsonObject.getString("Country"));
                        movieList.add(movie);

                        firebaseFirestore.collection("users")
                                .document(auth.getCurrentUser().getEmail())
                                .collection("Filmes").document(movie.getTitle()).set(movie)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid){
                                        Log.d("TAG", "oi");
                                    }
                                });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(movieList);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(Main3Activity.this);
        requestQueue.add(request);

    }

    private void filtrarFilmes(String emailUser, final Boolean favorito, final String genero){
        final List<Movie> movieFilter = new ArrayList<>();
        firebaseFirestore.collection("users").document(emailUser)
                .collection("Filmes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentoQuery : task.getResult()){
                                Movie movie = documentoQuery.toObject(Movie.class);
                                if(favorito){
                                    if(documentoQuery.getBoolean("favorito")){
                                        movieFilter.add(movie);
                                    }
                                }
                                if(!genero.isEmpty()){
                                    if(documentoQuery.getString("genre").contains(genero)){
                                        movieFilter.add(movie);
                                    }
                                }
                            }
                        }
                        setuprecyclerview(movieFilter);
                    }
                });
    }


    private void setuprecyclerview(List<Movie> movieList){

        RecyclerViewAdapter myadapter =  new RecyclerViewAdapter(this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }
}
