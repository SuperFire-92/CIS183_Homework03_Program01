package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv_j_ma_userList;
    Button btn_j_ma_addUser;
    ArrayList<User> userList;
    ListAdapter adapter;
    Intent int_j_addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attach the variable to their activity counterparts
        lv_j_ma_userList = findViewById(R.id.lv_v_ma_userList);
        btn_j_ma_addUser = findViewById(R.id.btn_v_ma_addUser);

        int_j_addUser = new Intent(MainActivity.this, AddUser.class);

        //Make a new instance of the userList
        userList = new ArrayList<User>();

        //Set the listview to show the list
        fillListView();

        setAddUserButtonEvent();

        //See if anything was passed from a different intent

        Bundle info = getIntent().getExtras();
        if (info == null)
        {
            //Nothing was passed
            //Log.d("info","null");
        }
        else
        {
            //Something was passed, figure out what it was

        }
    }

    public void setAddUserButtonEvent()
    {
        btn_j_ma_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //User u = new User("Jim","Bob","jm22_","mjimB23","JimBob@gmail.com",22);

                //userList.add(u);

                //adapter.notifyDataSetChanged();

                startActivity(int_j_addUser);
            }
        });
    }

    public void fillListView()
    {
        adapter = new ListAdapter(this, userList);

        lv_j_ma_userList.setAdapter(adapter);
    }
}