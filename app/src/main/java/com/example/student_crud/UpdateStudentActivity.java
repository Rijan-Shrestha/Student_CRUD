package com.example.student_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.student_crud.database.DbHelper;
import com.example.student_crud.model.Student;

public class UpdateStudentActivity extends AppCompatActivity {

    private EditText Nameet;
    private EditText Emailet;
    private EditText Phoneet;
    private Button UpdateBtn;

    private DbHelper dbHelper;
    private int receivedStudentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);



        //init
        Nameet = findViewById(R.id.studentNameUpdate);
        Emailet = findViewById(R.id.studentEmailUpdate);
        Phoneet = findViewById(R.id.studentPhoneUpdate);
        UpdateBtn = findViewById(R.id.updateStudentButton);

        dbHelper = new DbHelper(this);

        try {
            //get intent to get person id
            receivedStudentId = getIntent().getIntExtra("Student_ID", 1);
            Log.d("id",String.valueOf(receivedStudentId));


        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate user data before update***/
//        //set field to this user data
        Student  queriedStudent = dbHelper.getStudentById(receivedStudentId);

        Nameet.setText(queriedStudent.getName());
        Emailet.setText(queriedStudent.getEmail());
        Phoneet.setText(queriedStudent.getPhone());




        //listen to add button click to update
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updateStudent();
            }
        });

    }

    private void updateStudent(){
        String name = Nameet.getText().toString().trim();
        String email = Emailet.getText().toString().trim();
        String phone = Phoneet.getText().toString().trim();


        if(name.isEmpty()){
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(email.isEmpty()){
            Toast.makeText(this, "You must enter an email", Toast.LENGTH_SHORT).show();
        }

        if(phone.isEmpty()){
            Toast.makeText(this, "You must enter phone", Toast.LENGTH_SHORT).show();
        }


//        //create updated student
        Student updated = new Student(receivedStudentId,name,email,phone);
//
//        //call dbhelper update
        if(dbHelper.updatStudent(updated)){
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(this, AllStudentActivity.class));
    }

}
