package com.example.master;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

public class UserProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView User_dp;
    TextView user_dp_textview;
    //select_course,select_project,select_duration,select_intrest,name;
    EditText namebox;
    Spinner SelectProjectSpinner, SelectCourseSpinner, SelectDurationSpinner;
    RadioButton PersonalIntrest, ProfessionalIntrest;
    Button SaveBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        User_dp = (ImageView) findViewById(R.id.dp);
        user_dp_textview = (TextView) findViewById(R.id.select_profile_textview);
        namebox = (EditText) findViewById(R.id.name_box);
        SelectCourseSpinner = (Spinner) findViewById(R.id.course_spinner);
        SelectProjectSpinner = (Spinner) findViewById(R.id.project_spinner);
        SelectDurationSpinner = (Spinner) findViewById(R.id.duration_spinner);
        PersonalIntrest = (RadioButton) findViewById(R.id.personal_intrest);
        ProfessionalIntrest = (RadioButton) findViewById(R.id.professional_intrest);
        SaveBtn = (Button) findViewById(R.id.Save_btn);
        SetContentofDuartionSpinner(SelectDurationSpinner);
        SetContentofProjectSpinner(SelectProjectSpinner);
        SetContentofCourseSpinner(SelectCourseSpinner);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, position + "  id: " + id + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "NOthing Selected ", Toast.LENGTH_SHORT).show();

    }
    //functions
    public void SetContentofCourseSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Course_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void SetContentofDuartionSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Duartion_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void SetContentofProjectSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Project_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }
}
