package com.example.homework03_program01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaParser;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Users.db";
    private static final String TABLE_NAME    = "Users";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,2);
    }

    //Builds the table with the given variables per entry
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String to hold the statement in, then run it as an SQL statement
        String statement = "CREATE TABLE " + TABLE_NAME + " (username TEXT PRIMARY KEY NOT NULL, firstname TEXT, lastname TEXT, password TEXT, email TEXT, age TEXT);";
        db.execSQL(statement);
    }

    //Used to create a new table if the version number is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Delete the table upon upgrading
        String statement = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(statement);

        //Create a new table
        onCreate(db);
    }

    //Used to find the size of the table
    public int numOfRowsInTable()
    {
        //Create a readable version of the database
        SQLiteDatabase db = this.getReadableDatabase();

        //Get the number of rows in the database
        int numRows = (int) DatabaseUtils.queryNumEntries(db,TABLE_NAME);

        //Close the database
        db.close();

        //Return the number of rows
        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<User> getAllRows()
    {
        //Create an ArrayList to hold the data
        ArrayList<User> listOfUsers = new ArrayList<User>();

        //Create the query statement to be used
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY username;";

        //Create a readable version of the database
        SQLiteDatabase db = this.getReadableDatabase();

        //Query the table
        Cursor cursor = db.rawQuery(selectQuery, null);

        String fName;
        String lName;
        String username;
        String password;
        String email;
        String age;
        if (cursor.moveToFirst())
        {
            do {
                //Get every variable from each row in the table
                fName = cursor.getString(cursor.getColumnIndex("firstname"));
                lName = cursor.getString(cursor.getColumnIndex("lastname"));
                username = cursor.getString(cursor.getColumnIndex("username"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                email = cursor.getString(cursor.getColumnIndex("email"));
                age = cursor.getString(cursor.getColumnIndex("age"));

                //Put the new user into the arraylist
                listOfUsers.add(new User(fName,lName,username,password,email,age));
            } while (cursor.moveToNext());
        }

        db.close();

        return listOfUsers;
    }

    public boolean addNewUser(User u)
    {
        if (checkUniquePrimaryKey(u.getuName()))
        {
            //Create a writable version of the database
            SQLiteDatabase db = this.getWritableDatabase();

            //Insert the user into the table
            String statement = "INSERT INTO " + TABLE_NAME + " VALUES ('" + u.getuName() + "','" + u.getfName() + "','" + u.getlName() + "','" + u.getPassword() + "','" + u.getEmail() + "','" + u.getAge() + "');";

            db.execSQL(statement);

            return true;
        }
        else
        {
            return false;
        }
    }

    @SuppressLint("Range")
    public boolean checkUniquePrimaryKey(String u)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Boolean unique = true;

        String selectQuery = "SELECT username FROM " + TABLE_NAME + " ORDER BY username;";

        Cursor cursor = db.rawQuery(selectQuery, null);

        //Scan the database to check if there is a match to the given username string
        if (cursor.moveToFirst())
        {
            do {
                if (cursor.getString(cursor.getColumnIndex("username")).equals(u))
                {
                    unique = false;
                }
            } while (cursor.moveToNext());
        }

        db.close();

        return unique;
    }

    @SuppressLint("Range")
    public User getUserAtIndex(int index)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY username;";

        Cursor cursor = db.rawQuery(selectQuery, null);

        String fName;
        String lName;
        String username;
        String password;
        String email;
        String age;
        User u;

        //Attempt to move the cursor to the given index
        if (cursor.moveToPosition(index))
        {
            //Create the user stored in the index
            fName = cursor.getString(cursor.getColumnIndex("firstname"));
            lName = cursor.getString(cursor.getColumnIndex("lastname"));
            username = cursor.getString(cursor.getColumnIndex("username"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            email = cursor.getString(cursor.getColumnIndex("email"));
            age = cursor.getString(cursor.getColumnIndex("age"));
            u = new User(fName,lName,username,password,email,age);
        }
        else
        {
            //Return a null user if the index did not exist
            u = new User("NULL","NULL","NULL","NULL","NULL","NULL");
        }

        db.close();

        return u;
    }

    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Updates a user based on the provided information
        String statement = "UPDATE " + TABLE_NAME + " SET " +
                "firstname = '" + u.getfName() + "', " +
                "lastname = '" + u.getlName() + "', " +
                "password = '" + u.getPassword() + "', " +
                "email = '" + u.getEmail() + "', " +
                "age = '" + u.getAge() + "' WHERE username = '" + u.getuName() + "';";

        db.execSQL(statement);

        db.close();
    }

    public void deleteUser(String u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Delete a user based off a provided string
        String statement = "DELETE FROM " + TABLE_NAME + " WHERE username = '" + u + "';";
        db.execSQL(statement);

        db.close();
    }

    public void deleteUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Delete a user based off a provided user
        String statement = "DELETE FROM " + TABLE_NAME + " WHERE username = '" + u.getuName() + "';";
        db.execSQL(statement);

        db.close();
    }
}
