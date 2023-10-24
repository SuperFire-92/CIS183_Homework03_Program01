package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayUser extends AppCompatActivity {
    TextView tv_j_du_fullName;
    TextView tv_j_du_username;
    TextView tv_j_du_password;
    TextView tv_j_du_email;
    TextView tv_j_du_age;
    Button btn_j_du_back;
    Button btn_j_du_update;
    Intent int_j_main;
    Intent int_j_updateUser;
    DatabaseHelper dbHelper;
    int listNum;
    User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);
        //Set up all variables
        tv_j_du_fullName = findViewById(R.id.txt_v_du_fullName);
        tv_j_du_username = findViewById(R.id.txt_v_du_username);
        tv_j_du_password = findViewById(R.id.txt_v_du_password);
        tv_j_du_email    = findViewById(R.id.txt_v_du_email);
        tv_j_du_age      = findViewById(R.id.txt_v_du_age);
        btn_j_du_back    = findViewById(R.id.btn_v_du_back);
        btn_j_du_update  = findViewById(R.id.btn_v_du_update);
        int_j_main       = new Intent(DisplayUser.this, MainActivity.class);
        int_j_updateUser = new Intent(DisplayUser.this, UpdateUser.class);
        dbHelper         = new DatabaseHelper(this);

        //Get the index of the requested employee
        Intent cameFrom = getIntent();
        Bundle bundle = cameFrom.getExtras();
        listNum = bundle.getInt("itemInList");

        //Set the curUser to the user at the index
        curUser = dbHelper.getUserAtIndex(listNum);

        //Log.d("Number received",listNum + "");

        displayData();

        //Set button events
        setUpdateEvent();
        setBackEvent();
    }

    public void displayData()
    {
        tv_j_du_fullName.setText(curUser.getfName() + " " + curUser.getlName());
        tv_j_du_username.setText(curUser.getuName());
        tv_j_du_password.setText(curUser.getPassword());
        tv_j_du_email.setText(curUser.getEmail());
        tv_j_du_age.setText(curUser.getAge());
    }

    public void setUpdateEvent()
    {
        btn_j_du_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_j_updateUser.putExtra("itemInList",listNum);
                startActivity(int_j_updateUser);
            }
        });
    }

    public void setBackEvent()
    {
        btn_j_du_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(int_j_main);
            }
        });
    }
}