package com.example.cardiacrecorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    Context context;
    ArrayList<DataClass>list;

    public MyAdapter(Context context, ArrayList<DataClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass dataClass = list.get(position);
        holder.sys.setText(dataClass.getSys());
        holder.dis.setText(dataClass.getDis());
        holder.bpm.setText(dataClass.getBpm());
        holder.cmnt.setText(dataClass.getCmnt());

        holder.editimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, edit.class);

                intent.putExtra("username", list.get(holder.getAdapterPosition()).getPasseduser());
                intent.putExtra("id", list.get(holder.getAdapterPosition()).getId());

                context.startActivity(intent);
            }
        });

        holder.delimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete from list
                String user = list.get(holder.getAdapterPosition()).getPasseduser();
                String id = list.get(holder.getAdapterPosition()).getId();
                delete_data(user,id);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("key", user);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, details.class);
                intent.putExtra("username", list.get(holder.getAdapterPosition()).getPasseduser());
                intent.putExtra("id", list.get(holder.getAdapterPosition()).getId());

                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sys,dis,bpm,cmnt;
        ImageView editimg,delimg;
        CardView recCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sys = itemView.findViewById(R.id.un1);
            dis = itemView.findViewById(R.id.un2);
            bpm = itemView.findViewById(R.id.un3);
            cmnt = itemView.findViewById(R.id.textView10);
            editimg = itemView.findViewById(R.id.image2);
            delimg = itemView.findViewById(R.id.image);
            recCard = itemView.findViewById(R.id.recCard);
        }
    }

    public void delete_data(String user,String id){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("data");
        DatabaseReference itemRef = reference.child(user).child(id);
        itemRef.removeValue();
    }
}
