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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TelaInicial extends RecyclerView.Adapter<TelaInicial.ViewHolder> {

    private Context conteudo;
    private List<Books> data;

    public TelaInicial(Context conteudo, List<Books> data){
        this.conteudo = conteudo;
        this.data = data;
    }
    @Override
    public TelaInicial.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(conteudo);
        view = inflater.inflate(R.layout.cardview_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TelaInicial.ViewHolder holder, int position) {
        holder.book_title.setText(data.get(position).getTitulo());
        holder.book_thumbnail.setImageResource(data.get(position).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView book_title;
        ImageView book_thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);

            book_title = (TextView) itemView.findViewById(R.id.id_title_book);
            book_thumbnail = (ImageView) itemView.findViewById(R.id.id_image_book);
        }
    }

}