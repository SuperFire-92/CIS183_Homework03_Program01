package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<User> listOfUsers;

    public ListAdapter(Context c, ArrayList<User> ls)
    {
        context = c;
        listOfUsers = ls;
    }

    @Override
    public int getCount() {
        return listOfUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            //Find the cell
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell, null);
        }

        //Get the gui elements
        TextView fullName = view.findViewById(R.id.tv_v_cc_fullName);
        TextView username = view.findViewById(R.id.tv_v_cc_username);

        //Get the requested user
        User user = listOfUsers.get(i);

        //Set the gui for this cell
        fullName.setText(user.getfName() + " " + user.getlName());
        username.setText(user.getuName());

        return view;
    }
}