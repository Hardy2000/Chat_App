package com.hardy.chatappfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 1000;

    TextView s, b_text;

    Animation bottomAnim, bounceAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT < 16) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        } else {
//            View decorView = getWindow().getDecorView();
//            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//            decorView.setSystemUiVisibility(uiOptions);
//            ActionBar actionBar = getActionBar();
//            actionBar.hide();
//        }

        //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.buttom_animation);

        bounceAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);


        s = findViewById(R.id.s);
        b_text = findViewById(R.id.b_text);

//        b_text.setAnimation(bottomAnim);
//        myBounceInterpolator interpolator = new myBounceInterpolator(0.5, 20);
//        bounceAnim.setInterpolator(interpolator);
//        s.setAnimation(bounceAnim);

        //Splash Screen

        //time delay 5 sec
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, PhoneNumberAuth.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIMEOUT);

    }

}