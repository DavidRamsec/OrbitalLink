package com.example.orbitallink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MisionAdapter extends RecyclerView.Adapter<MisionAdapter.MisionViewHolder>{
    private List<Mision> listaElementos;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Mision item);
    }
    public MisionAdapter(List<Mision> listaMisiones, OnItemClickListener listener) {
        this.listaElementos = listaMisiones;
        this.listener = listener;
    }
    public MisionAdapter(List<Mision> listaMisiones) {
        this.listaElementos = listaMisiones;
    }
    @NonNull
    @Override
    public MisionAdapter.MisionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.modelo_mision, parent, false);
        return new MisionAdapter.MisionViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MisionAdapter.MisionViewHolder holder, int position) {
        Mision mision = listaElementos.get(position);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(mision));

        holder.tvMision.setText(mision.getMision());
        holder.tvAgencia.setText(mision.getAgencia());

        Glide.with(holder.itemView.getContext())
                .load(mision.getRocket())
                .into(holder.imgMision);
    }
    @Override
    public int getItemCount() {
        return listaElementos.size();
    }

    public static class MisionViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMision;
        TextView tvMision;
        TextView tvAgencia;
        public MisionViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMision = itemView.findViewById(R.id.ivImage);
            tvMision = itemView.findViewById(R.id.tvMisionName);
            tvAgencia = itemView.findViewById(R.id.tvAgencia);
        }
    }
}
