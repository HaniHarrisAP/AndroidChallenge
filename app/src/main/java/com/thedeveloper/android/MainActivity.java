package com.thedeveloper.android;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lay=(LinearLayout)findViewById(R.id.lay);
        lay.setVisibility(View.INVISIBLE);

        Button login=(Button)findViewById(R.id.login);
        Button register=(Button)findViewById(R.id.register);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lay.setVisibility(View.VISIBLE);
            }
        }, 2000);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in1, R.anim.slide_out1);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in1, R.anim.slide_out1);
            }
        });

    }
}
