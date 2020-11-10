package com.example.contacts.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "contents")
    public String contents;

    @ColumnInfo(name = "title")
    public String title;

    //todo implement
    @ColumnInfo(name = "phoneNumber")
    public long phoneNumber;

    @ColumnInfo(name = "email")
    public String email;
}