package com.example.homework03_program01;

public class User {
    private String fName;
    private String lName;
    private String uName;
    private String password;
    private String email;
    private int age;

    public User()
    {

    }

    public User(String f, String l, String u, String p, String e, int a)
    {
        fName = f;
        lName = l;
        uName = u;
        password = p;
        email = e;
        age = a;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String f) {
        fName = f;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String l) {
        lName = l;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String u) {
        uName = u;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        password = p;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        email = e;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int a) {
        age = a;
    }




}
