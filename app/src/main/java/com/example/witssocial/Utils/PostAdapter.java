package com.example.witssocial.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.witssocial.Model.Post;
import com.example.witssocial.Model.User;
import com.example.witssocial.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private final PostRecyclerViewInterface postRecyclerViewInterface;
    Context context;
    ArrayList<Post> list;
    DatabaseReference database, postsRef,userRef;

    private FirebaseUser firebaseUser;

    public PostAdapter(Context context, ArrayList<Post> list, PostRecyclerViewInterface postRecyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.postRecyclerViewInterface = postRecyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view, postRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = list.get(position);

        holder.time.setText(post.getTime());
        holder.username.setText(post.getUsername());
        holder.caption.setText(post.getCaption());
/*
        DatabaseReference finalTime = FirebaseDatabase.getInstance().getReference().child("Posts").child("-N3Khiit9DZyjpHUWTTQ").child("time");
        finalTime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String data = snapshot.getRef().getParent().getKey();
                    Objects.requireNonNull(holder).time.setText(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        Glide.with(holder.itemView).load(list.get(position).getImage()).into(holder.post_image);


        //setProfile picture for each user

        database = FirebaseDatabase.getInstance().getReference("Users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String postUsername = post.getUsername();
                    String username = dataSnapshot.child("username").getValue(String.class);

                    if( username != null)
                    {
                        if( username.equals(postUsername))
                        {

                            Glide.with(holder.itemView).load(dataSnapshot.child("imageurl").getValue(String.class))
                                    .into(holder.profile_picture);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView username, caption, likes, time;
        ImageView post_image, like, profile_picture;

       public ViewHolder(@NonNull View itemView, PostRecyclerViewInterface postRecyclerViewInterface) {
           super(itemView);

           username = itemView.findViewById(R.id.username);
           caption = itemView.findViewById(R.id.caption);
           post_image = itemView.findViewById(R.id.post_image);
           post_image.setClipToOutline(true);
           like = itemView.findViewById(R.id.like);
           likes = itemView.findViewById(R.id.likes);
           profile_picture = itemView.findViewById(R.id.image_profile);
           time = itemView.findViewById(R.id.time);





           //Attach onclick lister to the item view
           username.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(postRecyclerViewInterface != null){
                        int position = getBindingAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            postRecyclerViewInterface.onUsernameClick(position);
                        }
                   }
               }
           });

           like.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   like.setImageResource(R.drawable.ic_baseline_favorite_24);
                   likes.setTextColor(Color.parseColor("#0057B8"));
               }
           });
       }
   }

}
