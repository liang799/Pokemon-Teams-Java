package com.sp.poketeams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Customadapter extends RecyclerView.Adapter<Customadapter.MyViewHolder>{

    Context context;
    ArrayList poketype, pokename;

    ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    Customadapter(Context context, ArrayList pokename, ArrayList poketype){
        this.context = context;
        this.pokename = pokename;
        this.poketype = poketype;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);

        MyViewHolder evh = new MyViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pokemon currentitem = pokemonArrayList.get(position);

        holder.pokenametxt.setText("Pokemon: " + pokename.get(position) );
        holder.poketypetxt.setText("Type: " + poketype.get(position));

    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView pokenametxt ,poketypetxt;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            pokenametxt = itemView.findViewById(R.id.pokemonname);
            poketypetxt = itemView.findViewById(R.id.type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
