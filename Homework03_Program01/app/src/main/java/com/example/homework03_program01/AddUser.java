package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class AddUser extends AppCompatActivity {
    EditText et_j_au_fName;
    EditText et_j_au_lName;
    EditText et_j_au_username;
    EditText et_j_au_email;
    EditText et_j_au_password;
    EditText et_j_au_age;

    Intent int_j_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //Assign variables their gui counterparts
        et_j_au_fName    = findViewById(R.id.et_v_au_fName);
        et_j_au_lName    = findViewById(R.id.et_v_au_lName);
        et_j_au_username = findViewById(R.id.et_v_au_username);
        et_j_au_email    = findViewById(R.id.et_v_au_email);
        et_j_au_password = findViewById(R.id.et_v_au_password);
        et_j_au_age      = findViewById(R.id.et_v_au_age);

        int_j_main       = new Intent(AddUser.this, MainActivity.class);


    }
}