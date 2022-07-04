package com.example.pancaranticket.Page;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pancaranticket.loginActivity;
import com.example.pancaranticket.R;

public class SplashScreen extends AppCompatActivity {

    ImageView Img;
    TextView Txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Img = findViewById(R.id.logo);
        Txt = findViewById(R.id.textlogoadikberadik);
        Animation anime = AnimationUtils.loadAnimation(this,R.anim.side_slide);
        Img.startAnimation(anime);
        Txt.startAnimation(anime);

        Intent i = new Intent(this , loginActivity.class);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}

