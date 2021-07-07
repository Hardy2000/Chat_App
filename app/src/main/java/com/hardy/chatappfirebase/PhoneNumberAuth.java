package com.hardy.chatappfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.hardy.chatappfirebase.databinding.ActivityPhoneNumberAuthBinding;

public class PhoneNumberAuth extends AppCompatActivity {
    ActivityPhoneNumberAuthBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneNumberAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(PhoneNumberAuth.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        getSupportActionBar().hide();
        binding.phoneNumber.requestFocus();
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(PhoneNumberAuth.this, OtpActivity.class);
                intent2.putExtra("phonenumber", binding.phoneNumber.getText().toString());
                startActivity(intent2);
            }
        });
    }
}