package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    Intent int_j_displayUser;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attach the variable to their activity counterparts
        lv_j_ma_userList  = findViewById(R.id.lv_v_ma_userList);
        btn_j_ma_addUser  = findViewById(R.id.btn_v_ma_addUser);
        int_j_addUser     = new Intent(MainActivity.this, AddUser.class);
        int_j_displayUser = new Intent(MainActivity.this, DisplayUser.class);

        //Make a new instance of the userList
        userList = new ArrayList<User>();

        //Make a new instance of the DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        //Set the userList ArrayList to match the database
        userList = dbHelper.getAllRows();

        //Set the listview to show the list
        fillListView();


        setAddUserButtonEvent();
        setListClickEvent();
        setListLongClickEvent();
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

    public void setListClickEvent()
    {
        lv_j_ma_userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Log.d("Listview clicked",i + "");

                int_j_displayUser.putExtra("itemInList",i);
                startActivity(int_j_displayUser);
            }
        });
    }

    public void setListLongClickEvent()
    {
        lv_j_ma_userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Deletes the clicked user
                dbHelper.deleteUser(userList.get(i));
                userList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void fillListView()
    {
        adapter = new ListAdapter(this, userList);

        lv_j_ma_userList.setAdapter(adapter);
    }
}