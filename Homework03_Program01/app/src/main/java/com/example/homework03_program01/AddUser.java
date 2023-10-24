package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddUser extends AppCompatActivity {
    EditText et_j_au_fName;
    EditText et_j_au_lName;
    EditText et_j_au_username;
    EditText et_j_au_email;
    EditText et_j_au_password;
    EditText et_j_au_age;
    TextView tv_j_au_errorText;
    Button btn_j_au_back;
    Button btn_j_au_addUser;
    Intent int_j_main;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //Assign variables their gui counterparts
        et_j_au_fName     = findViewById(R.id.et_v_au_fName);
        et_j_au_lName     = findViewById(R.id.et_v_au_lName);
        et_j_au_username  = findViewById(R.id.et_v_au_username);
        et_j_au_email     = findViewById(R.id.et_v_au_email);
        et_j_au_password  = findViewById(R.id.et_v_au_password);
        et_j_au_age       = findViewById(R.id.et_v_au_age);
        tv_j_au_errorText = findViewById(R.id.tv_v_au_errorText);
        btn_j_au_back     = findViewById(R.id.btn_v_au_back);
        btn_j_au_addUser  = findViewById(R.id.btn_v_au_addUser);

        int_j_main        = new Intent(AddUser.this, MainActivity.class);

        dbHelper = new DatabaseHelper(this);

        backEvent();
        addUserEvent();
    }

    public void addUserEvent()
    {
        btn_j_au_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = et_j_au_fName.getText().toString();
                String lName = et_j_au_lName.getText().toString();
                String username = et_j_au_username.getText().toString();
                String email = et_j_au_email.getText().toString();
                String password = et_j_au_password.getText().toString();
                String age = et_j_au_age.getText().toString();
                if (fName.equals("") || lName.equals("") || username.equals("") || email.equals("") || password.equals("") || age.equals(""))
                {
                    tv_j_au_errorText.setText("All Textboxes Must Be Filled");
                }
                else if (!dbHelper.checkUniquePrimaryKey(username))
                {
                    tv_j_au_errorText.setText("Unique Username Required");
                }
                else if (!isNumber(age))
                {
                    tv_j_au_errorText.setText("Age Must Be A Number");
                }
                else
                {
                    User user = new User(fName,lName,username,password,email,age);

                    dbHelper.addNewUser(user);

                    clearTextBoxes();
                }
            }
        });
    }

    public void backEvent()
    {
        btn_j_au_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(int_j_main);
            }
        });
    }

    public void clearTextBoxes()
    {
        et_j_au_fName.setText("");
        et_j_au_lName.setText("");
        et_j_au_username.setText("");
        et_j_au_email.setText("");
        et_j_au_password.setText("");
        et_j_au_age.setText("");
    }

    public boolean isNumber(String a)
    {
        boolean valid = true;
        for (int i = 0; i < a.length(); i++)
        {
            if (!(a.charAt(i) >= '0' && a.charAt(i) <= '9'))
            {
                Log.d("Invalid Number:", a.charAt(i) + "");
                valid = false;
            }
        }

        return valid;
    }
}