package com.example.yaglimi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pill {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "pillname")
    public String pillname;
    @ColumnInfo(name = "buydate")
    public String buydate;
    @ColumnInfo(name = "date")
    public String date;
}
