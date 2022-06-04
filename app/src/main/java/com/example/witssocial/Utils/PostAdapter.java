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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private final PostRecyclerViewInterface postRecyclerViewInterface;
    Context context;
    static ArrayList<Post> list;
    DatabaseReference database, postsRef,userRef;
    static int  clicked = 0;
    String postid;

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
        if(post != null){
            String timeAgo = TimeAgo.getTimeAgo(post.getTime());

            //Get time stamp
            holder.time.setText(timeAgo);
            holder.username.setText(post.getUsername());
            holder.caption.setText(post.getCaption());
            holder.like.setImageResource(R.drawable.ic_like);


            if (post.getImage() != null) {
                Glide.with(holder.itemView).load(list.get(position).getImage()).into(holder.post_image);
            }

            //setProfile picture for each user

            database = FirebaseDatabase.getInstance().getReference("Users");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        String postUsername = post.getUsername();
                        if(dataSnapshot.child("username").getValue() != null){
                            String username = dataSnapshot.child("username").getValue(String.class);

                            if (username != null) {
                                if (username.equals(postUsername)) {

                                    Glide.with(holder.itemView).load(dataSnapshot.child("imageurl").getValue(String.class))
                                            .into(holder.profile_picture);

                                }
                            }
                        }


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            // getting the number of likes for each post
            DatabaseReference likeRef = FirebaseDatabase.getInstance().getReference("Liked");
            DatabaseReference likedPost = likeRef.child(post.getPostid());
            DatabaseReference likeschild = likedPost.child("likes");
            likeschild.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    long number_of_likes = snapshot.getChildrenCount();
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        holder.likes.setText(Integer.toString(Math.toIntExact(number_of_likes)));

                        if (snapshot.child(currentUser).exists()) {
                            if (snapshot.child(currentUser).getValue(Boolean.class)) {
                                holder.like.setImageResource(R.drawable.ic_baseline_favorite_24);
                            }
                        }
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
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

            //Like button function
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    like.setImageResource(R.drawable.ic_baseline_favorite_24);

                    int position = getBindingAdapterPosition();
                    Post post = list.get(position);
                    //get the current user to store their userid
                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    //Getting a likes reference
                    DatabaseReference likeRef  = FirebaseDatabase.getInstance().getReference("Liked");
                    DatabaseReference likedPost = likeRef.child(post.getPostid());

                    DatabaseReference likeschild = likedPost.child("likes");

                    DatabaseReference userLikedPost = likeschild.child(current_user.getUid());
                    userLikedPost.setValue(true);
                    /*if(clicked ==1)
                    {

                        //Toast.makeText(like.getContext(), post.getPostid() + " has been unliked", Toast.LENGTH_SHORT).show();
                        Log.i("clicked is ==1, check ifs 1 ", Integer.toString(clicked));

                        clicked -=1;
                        Log.i("clicked is decreased by 1, check ifs 1 ", Integer.toString(clicked));

                        like.setImageResource(R.drawable.ic_like);
                        likes.setText(Integer.toString(clicked));
                        likes.setTextColor(Color.parseColor("#0057B8"));


                    }
                    Log.i("clicked is < 0, check ifs 2 ", Integer.toString(clicked));
                    if(clicked<1)
                    {
                        clicked +=1;
                        Log.i("clicked is == 1, now inside ifs 2 ", Integer.toString(clicked));
                        like.setImageResource(R.drawable.ic_baseline_favorite_24);
                        likes.setText(Integer.toString(clicked));
                        likes.setTextColor(Color.parseColor("#0057B8"));
                    }*/

                }


            });


        }

    }

}