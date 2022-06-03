package com.example.witssocial.CreatePost;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.witssocial.Home.HomeActivity;
import com.example.witssocial.Model.User;
import com.example.witssocial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class PostActivity extends AppCompatActivity {

    private Button btnUpload;
    private ImageButton btnRemove;
    private ImageView imageView;
    private EditText editText;
    private ProgressBar progressBar;
    String username;

    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    UploadTask uploadTask;
    FirebaseDatabase Database;
    DatabaseReference myRef,users;
    private final int PICK_IMAGE_REQUEST = 71;
    SharedPreferences sp;
    // fetcing an image
    String imageUrl;
    FirebaseUser user;
    String  userCaption,postkey;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // showing the back button in action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(myToolbar);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Create Post");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initialize Views
        btnRemove = (ImageButton) findViewById(R.id.clear);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgView);
        editText = (EditText) findViewById(R.id.caption);
        progressBar = (ProgressBar) findViewById(R.id.pb_createPost);

        Database = FirebaseDatabase.getInstance();
        myRef = Database.getReference("Posts");
        users = Database.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        sp = getSharedPreferences("User_info", MODE_PRIVATE);
        String name = sp.getString("name", "null");

        //initally disable the post button
        btnUpload.setEnabled(false);

        //Hide Progress bar
        progressBar.setVisibility(View.GONE);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageBitmap(null);

                //disable the post button
                btnUpload.setEnabled(false);
                imageView.setBackgroundResource(R.drawable.ic_add_image);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //if Image exists
                if(imageView.getDrawable() != null){
                    imageView.setBackgroundResource(android.R.color.transparent);
                }

                chooseImage();

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();

            //enable the button
            btnUpload.setEnabled(true);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    //StorageReference storageReference1 = storageReference.child(System.currentTimeMillis()+"."+GetFileExtension(filePath));
    //        storageReference1.putFile(filePath)

    private void uploadImage() {

        if (filePath != null) {
            postkey = myRef.push().getKey(); //gets the unique key
            
            DatabaseReference dbRef = myRef.child(postkey);
            StorageReference riversRef = storageReference.child("images/" + filePath.getLastPathSegment());
            uploadTask = riversRef.putFile(filePath);
            String name = user.getUid();
             username = getusername(name);


        // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                    // Handle unsuccessful uploads
                    Toast.makeText(PostActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    //Log.d(TAG,"Upload failed.");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...

                    //Add load button


                    //Display toast
                   Toast.makeText(PostActivity.this, "picture successfully posted", Toast.LENGTH_LONG).show();

                    //go to homepage
                    goToHomeActivity();

                   //gets the download link from firebase storage
                    riversRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {

                            imageUrl = task.getResult().toString();
                            String uniqueid = user.getUid();


                            DatabaseReference userid = dbRef.child("userid");
                            DatabaseReference imageRef = dbRef.child("image");
                            DatabaseReference nameRef = dbRef.child("username");
                            DatabaseReference captionRef = dbRef.child("Caption");

                            userCaption = editText.getText().toString();
                            captionRef.setValue(userCaption);
                            //Toast.makeText(MainActivity.this,"The download"+imageUrl,Toast.LENGTH_LONG).show();
                            imageRef.setValue(imageUrl);
                            nameRef.setValue(username);
                            userid.setValue(uniqueid);

                        }

                    });

                    // This gets timestamp: stored as utc
                    riversRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                        @Override
                        public void onSuccess(StorageMetadata storageMetadata) {
                            long time = storageMetadata.getCreationTimeMillis();
                            //String  timestamp = Long.toString(time);
                            DatabaseReference timeStamp = dbRef.child("time");
                            timeStamp.setValue(time); //stored as utc
                        }
                    });

                    //Log.d(TAG,"Upload Successful");
                }
            });

            riversRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                @Override
                public void onSuccess(@NonNull StorageMetadata storageMetadata) {
                    long time = storageMetadata.getCreationTimeMillis();
                    String timestamp = Long.toString(time);
                    DatabaseReference timeStamp = dbRef.child("timestamp");
                    timeStamp.setValue(timestamp);
                }
            });
        }
    }

    private void goToHomeActivity() {
        //Go to home fragment
        Intent intent = new Intent(PostActivity.this, HomeActivity.class);
        finish();
        startActivity(intent);
    }

    public String getusername(String userid){
        users.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = snapshot.getValue(User.class);
                username = user1.getUsername();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return username;
    }

    /**
     * Clear focus on touch outside for all EditText inputs.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}