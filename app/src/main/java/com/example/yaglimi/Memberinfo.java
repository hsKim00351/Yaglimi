package com.example.yaglimi;

public class Memberinfo {
    private String name;
    private String phonenumber;
    private String birth;

    public Memberinfo (String name, String phonenumber, String birth) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.birth = birth;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhonenumber(){
        return this.phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getBirth(){
        return this.birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
}
