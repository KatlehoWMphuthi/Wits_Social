package com.example.witssocial;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Random;

public class PostActivity extends AppCompatActivity {

    private Button btnUpload;
    private ImageButton btnRemove;
    private ImageView imageView;
    private EditText editText;
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
    String  userCaption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //Initialize Views
        btnRemove = (ImageButton) findViewById(R.id.clear);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgView);
        editText = (EditText) findViewById(R.id.caption);

        Database = FirebaseDatabase.getInstance();
        myRef = Database.getReference("User_post");
        users = Database.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        sp = getSharedPreferences("User_info", MODE_PRIVATE);
        String name = sp.getString("name", "null");

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageBitmap(null);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
                imageView.setBackgroundResource(android.R.color.transparent);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbRef2 = myRef.child("name");
                dbRef2.setValue(name);
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
            //postkey = myRef.push().getKey();
            Random random = new Random();

            int i = random.nextInt(6065 - 1065) + 1065;
            DatabaseReference dbRef = Database.getReference("User_post" + Integer.toString(i));
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

                    Toast.makeText(PostActivity.this, "picture successfully posted", Toast.LENGTH_LONG).show();


                    riversRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            imageUrl = task.getResult().toString();
                            DatabaseReference imageRef = dbRef.child("image");
                            DatabaseReference nameRef = dbRef.child("username");
                            DatabaseReference captionRef = dbRef.child("Caption");
                            userCaption = editText.getText().toString();
                            captionRef.setValue(userCaption);
                            //Toast.makeText(MainActivity.this,"The download"+imageUrl,Toast.LENGTH_LONG).show();
                            imageRef.setValue(imageUrl);
                            nameRef.setValue(username);
                        }

                    });
                    //Log.d(TAG,"Upload Successful");
                }
            });
        }
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
}