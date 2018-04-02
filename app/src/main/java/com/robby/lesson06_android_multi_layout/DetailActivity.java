package com.robby.lesson06_android_multi_layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.robby.lesson06_android_multi_layout.entity.Student;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_id)
    TextView lblId;
    @BindView(R.id.tv_name)
    TextView lblName;
    @BindView(R.id.tv_address)
    TextView lblAddress;
    @BindView(R.id.tv_phone)
    TextView lblPhone;
    @BindView(R.id.tv_department)
    TextView lblDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(getResources().getString(R.string.parcel_student))) {
            Student student = getIntent().getParcelableExtra(getResources().getString(R.string.parcel_student));
            lblId.setText(student.getId());
            lblName.setText(student.getFullName());
            lblAddress.setText(student.getAddress());
            lblPhone.setText(student.getPhone());
            lblDepartment.setText(student.getDepartment());
        }
    }
}
