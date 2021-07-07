package com.hardy.chatappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hardy.chatappfirebase.databinding.ActivitySetupProfileBinding;

public class SetupProfileActivity extends AppCompatActivity {
    ActivitySetupProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri selectImage;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        dialog=new ProgressDialog(this);
        dialog.setMessage("Creating profile...");
        dialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        binding.imageView.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,20);
        });
        binding.continueBtn.setOnClickListener(view -> {
            String name=binding.nameBox.getText().toString().trim();
            if(name.isEmpty())
            {
                binding.nameBox.setError("Please type your name");
                return;
            }
            dialog.show();

            if(selectImage!=null)
            {
                StorageReference reference=storage.getReference().child("Profiles").child(auth.getUid());
                reference.putFile(selectImage).addOnCompleteListener(task -> {
               if(task.isSuccessful()){
                   reference.getDownloadUrl().addOnSuccessListener(uri -> {
                  String imageUrl=uri.toString();
                  String uid=auth.getUid();
                  String phone=auth.getCurrentUser().getPhoneNumber();
                  String name1 =binding.nameBox.getText().toString();

                  User user=new User(uid, name1,phone,imageUrl);

                  database.getReference()
                          .child("users")
                          .child(uid)
                          .setValue(user)
                          .addOnSuccessListener(aVoid -> {
                      dialog.dismiss();
                         Intent intent=new Intent(SetupProfileActivity.this,MainActivity.class);
                         startActivity(intent);
                         finish();
                          });
                   });
               }
                });
            }
            else {
                String uid=auth.getUid();
                String phone=auth.getCurrentUser().getPhoneNumber();

                User user=new User(uid,name,phone,"No Image");

                database.getReference()
                        .child("users")
                        .child(uid)
                        .setValue(user)
                        .addOnSuccessListener(aVoid -> {
                            dialog.dismiss();
                            Intent intent = new Intent(SetupProfileActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        });
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data!=null)
        {
            if(data.getData()!=null){
                binding.imageView.setImageURI(data.getData());
                selectImage=data.getData();
            }
        }

    }
}