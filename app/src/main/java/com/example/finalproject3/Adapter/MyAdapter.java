package com.example.finalproject3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject3.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> history;

    public MyAdapter(Context context, ArrayList history){
        this.context = context;
        this.history = history;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.history.setText(String.valueOf(history.get(position)));
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView history;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            history = itemView.findViewById(R.id.txtHistory);
        }
    }
}
