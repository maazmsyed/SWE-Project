package com.swe.justslidin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.swe.justslidin.constants.Constants;

public class LoginMainMenuActivity extends AppCompatActivity {

    private static final Constants constants = new Constants();
    EditText textBoxName;
    Button button;

    String playerName = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_login);
    }
}