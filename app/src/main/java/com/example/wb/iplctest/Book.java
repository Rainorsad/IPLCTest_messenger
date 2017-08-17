package com.example.wb.iplctest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zhangchen on 2017/8/17.
 */

public class Book implements Parcelable{
    private String name;
    private String user;

    public Book(String name, String user) {
        this.name = name;
        this.user = user;
    }

    protected Book(Parcel in) {
        name = in.readString();
        user = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(user);
    }
}
