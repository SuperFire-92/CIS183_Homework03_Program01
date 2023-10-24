package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateUser extends AppCompatActivity {
    TextView tv_j_uu_errorText;
    TextView tv_j_uu_uName;
    EditText et_j_uu_fName;
    EditText et_j_uu_lName;
    EditText et_j_uu_password;
    EditText et_j_uu_email;
    EditText et_j_uu_age;
    Button btn_j_uu_back;
    Button btn_j_uu_updateUser;
    Intent int_j_displayUser;
    DatabaseHelper dbHelper;
    int listNum;
    User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        //Set up all variables
        tv_j_uu_errorText   = findViewById(R.id.tv_v_uu_errorText);
        et_j_uu_fName       = findViewById(R.id.et_v_uu_fName);
        et_j_uu_lName       = findViewById(R.id.et_v_uu_lName);
        tv_j_uu_uName       = findViewById(R.id.tv_v_uu_uName);
        et_j_uu_password    = findViewById(R.id.et_v_uu_password);
        et_j_uu_email       = findViewById(R.id.et_v_uu_email);
        et_j_uu_age         = findViewById(R.id.et_v_uu_age);
        btn_j_uu_back       = findViewById(R.id.btn_v_uu_back);
        btn_j_uu_updateUser = findViewById(R.id.btn_v_uu_updateUser);
        int_j_displayUser   = new Intent(UpdateUser.this, DisplayUser.class);
        dbHelper = new DatabaseHelper(this);

        //Get the index of the requested employee
        Intent cameFrom = getIntent();
        Bundle bundle = cameFrom.getExtras();
        listNum = bundle.getInt("itemInList");

        //Set the curUser to the user at the index
        curUser = dbHelper.getUserAtIndex(listNum);

        displayData();

        //Set button events
        setUpdateEvent();
        setBackEvent();
    }

    public void setUpdateEvent()
    {
        btn_j_uu_updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = et_j_uu_fName.getText().toString();
                String lName = et_j_uu_lName.getText().toString();
                String username = curUser.getuName();
                String email = et_j_uu_email.getText().toString();
                String password = et_j_uu_password.getText().toString();
                String age = et_j_uu_age.getText().toString();
                //Check if all of the text boxes are filled
                if (fName.equals("") || lName.equals("")  || email.equals("") || password.equals("") || age.equals(""))
                {
                    tv_j_uu_errorText.setText("All Textboxes Must Be Filled");
                }
                //Check if the age is a number
                else if (!isNumber(age))
                {
                    tv_j_uu_errorText.setText("Age Must Be A Number");
                }
                //Update the user
                else
                {
                    //Build the user
                    User user = new User(fName,lName,username,password,email,age);

                    //Update the user in the database
                    dbHelper.updateUser(user);

                    //Go back to the display menu
                    int_j_displayUser.putExtra("itemInList",listNum);
                    startActivity(int_j_displayUser);
                }
            }
        });
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

    public void setBackEvent()
    {
        btn_j_uu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_j_displayUser.putExtra("itemInList",listNum);
                startActivity(int_j_displayUser);
            }
        });
    }

    public void displayData()
    {
        et_j_uu_fName.setText(curUser.getfName());
        et_j_uu_lName.setText(curUser.getlName());
        tv_j_uu_uName.setText(curUser.getuName());
        et_j_uu_password.setText(curUser.getPassword());
        et_j_uu_email.setText(curUser.getEmail());
        et_j_uu_age.setText(curUser.getAge());
    }

}