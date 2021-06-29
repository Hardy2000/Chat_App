package com.hardy.chatappfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hardy.chatappfirebase.databinding.ActivityPhoneNumberAuthBinding;

public class PhoneNumberAuth extends AppCompatActivity {
    ActivityPhoneNumberAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneNumberAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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