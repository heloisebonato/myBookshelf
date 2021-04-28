package com.example.mybookshelf;

import java.util.ArrayList;
import java.util.List;

public class UsuarioSingleton {
    private static UsuarioSingleton instance = new UsuarioSingleton();

    private UsuarioSingleton(){}

    public static UsuarioSingleton getInstance(){
        return instance;
    }
    public ArrayList<Usuario> usuarioList = new ArrayList<>();
}

