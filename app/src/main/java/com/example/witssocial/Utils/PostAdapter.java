package com.example.witssocial.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.witssocial.Model.Post;
import com.example.witssocial.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    Context context;
    ArrayList<Post> list;

    public PostAdapter(Context context, ArrayList<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = list.get(position);
        holder.username.setText(post.getUsername());
        holder.caption.setText(post.getCaption());
        Glide.with(holder.itemView).load(list.get(position).getImage()).into(holder.post_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView username, caption;
        ImageView post_image;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           username = itemView.findViewById(R.id.username);
           caption = itemView.findViewById(R.id.caption);
           post_image = itemView.findViewById(R.id.post_image);
       }
   }
}
