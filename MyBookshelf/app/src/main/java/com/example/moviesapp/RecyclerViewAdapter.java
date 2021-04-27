package com.example.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context mContext;
    List<Movie> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Movie> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<Movie> getmData() {
        return mData;
    }

    public void setmData(List<Movie> mData) {
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.movie,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(mContext, Main4Activity.class);
                i.putExtra("tvTitulo", mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("tvAno", mData.get(viewHolder.getAdapterPosition()).getYear());
                i.putExtra("tvGenero", mData.get(viewHolder.getAdapterPosition()).getGenre());
                i.putExtra("ivID", mData.get(viewHolder.getAdapterPosition()).getPoster());
                i.putExtra("tvAvaliacao", mData.get(viewHolder.getAdapterPosition()).getRated());
                i.putExtra("tvLancamento", mData.get(viewHolder.getAdapterPosition()).getReleased());
                i.putExtra("tvResumo", mData.get(viewHolder.getAdapterPosition()).getPlot());
                i.putExtra("tvDuracao", mData.get(viewHolder.getAdapterPosition()).getRuntime());
                i.putExtra("tvDiretor", mData.get(viewHolder.getAdapterPosition()).getDirector());
                i.putExtra("tvLingua", mData.get(viewHolder.getAdapterPosition()).getLanguage());
                i.putExtra("tvPais", mData.get(viewHolder.getAdapterPosition()).getCountry());
                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitulo.setText(mData.get(position).getTitle());
        holder.tvGenero.setText(mData.get(position).getGenre());
        Glide.with(mContext).load(mData.get(position).getPoster()).apply(option).into(holder.ivId);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitulo;
        TextView tvGenero;
        ImageView ivId;
        LinearLayout view_container;

        public MyViewHolder(View itemView){
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvGenero = itemView.findViewById(R.id.tvGenero);
            ivId = itemView.findViewById(R.id.ivID);
            view_container = itemView.findViewById(R.id.container);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), Main4Activity.class);
            i.putExtra("position", getLayoutPosition());
            ((Activity) v.getContext()).startActivityForResult(i,1);
        }
    }
}
