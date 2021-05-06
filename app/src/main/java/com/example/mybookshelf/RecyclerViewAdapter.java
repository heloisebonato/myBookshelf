package com.example.mybookshelf;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context conteudo;
    private List<Books> data;

    public RecyclerViewAdapter(Context conteudo, List<Books> data){
        this.conteudo = conteudo;
        this.data = data;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(conteudo);
        view = inflater.inflate(R.layout.cardview_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.book_title.setText(data.get(position).getTitulo());
        holder.book_thumbnail.setImageResource(data.get(position).getThumbnail());
        holder.card_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View views) {

                Intent intent = new Intent(conteudo, SingleBook.class);

                //passando data para singleBook
                intent.putExtra("Titulo",data.get(position).getTitulo());
                intent.putExtra("Categoria",data.get(position).getCategoria());
                intent.putExtra("Opiniao",data.get(position).getOpiniao());
                intent.putExtra("Thumbnail",data.get(position).getThumbnail());
                //iniciando
                conteudo.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView book_title;
        ImageView book_thumbnail;
        CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);

            book_title = (TextView) itemView.findViewById(R.id.id_title_book);
            book_thumbnail = (ImageView) itemView.findViewById(R.id.id_image_book);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}