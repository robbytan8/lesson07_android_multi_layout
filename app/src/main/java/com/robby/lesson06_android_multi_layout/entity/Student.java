package com.robby.lesson06_android_multi_layout.entity;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Robby Tan
 */

public class Student implements Parcelable {

    @Getter
    @Setter
    private String id;
    @Setter
    @Getter
    private String firstName;
    @Setter
    @Getter
    private String lastName;
    @Setter
    @Getter
    private String department;
    @Setter
    @Getter
    private String address;
    @Setter
    @Getter
    private String phone;

    protected Student(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        department = in.readString();
        address = in.readString();
        phone = in.readString();
    }

    public Student() {
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getDetails() {
        return firstName.concat(" ").concat(lastName).concat(" (").concat(id).concat(")");
    }

    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(department);
        dest.writeString(address);
        dest.writeString(phone);
    }
}
