package com.example.buss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Individual_user> userArrayList;

    public MyAdapter(Context context, ArrayList<Individual_user> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Individual_user user=userArrayList.get(position);

        holder.name.setText((user.name));
        holder.srn.setText((user.srn));
        holder.email.setText((user.email));

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,srn,email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            srn=itemView.findViewById(R.id.srn);
            email=itemView.findViewById(R.id.mail);
        }
    }
}
