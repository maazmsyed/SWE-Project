package com.swe.justslidin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Button letsSlide = findViewById(R.id.letsSlideButton);

        letsSlide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent switchToMainMenu = new Intent(StartScreen.this, MainMenu.class);
                startActivity(switchToMainMenu);
            }
        });


    }
}
