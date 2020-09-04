package com.example.hw1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapt extends RecyclerView.Adapter<MyAdapt.ViewHolder> {
    private ArrayList<String> futureW;
    private ArrayList<String> description;

    public MyAdapt(ArrayList<String> data, ArrayList<String> description){
        this.futureW = data;
        this.description = description;
    }

    @NonNull
    @Override
    public MyAdapt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int vt) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days, parent,false);
        return new MyAdapt.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapt.ViewHolder holder, int position) {
holder.txtv.setText(futureW.get(position));
holder.txtv2.setText(description.get(position));
    }

    @Override
    public int getItemCount() {
        return futureW.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView  txtv;
        public TextView txtv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtv = (TextView) itemView.findViewById(R.id.futureDays);
            txtv2 = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
