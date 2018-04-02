package com.robby.lesson06_android_multi_layout;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robby.lesson06_android_multi_layout.adapter.StudentAdapter;
import com.robby.lesson06_android_multi_layout.entity.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements StudentAdapter.StudentDataClickedListener {

    @BindView(R.id.rv_students)
    RecyclerView rvStudents;

    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.populateStudentsData();
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.setAdapter(getStudentAdapter());
    }

    public StudentAdapter getStudentAdapter() {
        if (studentAdapter == null) {
            studentAdapter = new StudentAdapter();
            studentAdapter.setStudentDataClickedListener(this);
        }
        return studentAdapter;
    }

    private void populateStudentsData() {
        try {
            InputStream inputStream = getAssets().open("students.json");
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Student> students = new ArrayList<>();
            students.addAll(Arrays.asList(objectMapper.readValue(inputStream, Student[].class)));
            getStudentAdapter().setStudents(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStudentDataClicked(Student student) {
        if (findViewById(R.id.fl_fragment_container) != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(getResources().getString(R.string.parcel_student), student);
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_fragment_container, fragment);
            transaction.commit();
        } else {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(getResources().getString(R.string.parcel_student), student);
            startActivity(intent);
        }
    }
}
