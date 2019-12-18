package com.example.student_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.student_crud.database.DbHelper;
import com.example.student_crud.model.Student;



public class CrudOperation extends AppCompatActivity {
    EditText etName, etEmail, etPhone;
    Button btnSave,btnallstudent;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_operation);


        etName = findViewById(R.id.stname);
        etEmail = findViewById(R.id.stemail);
        etPhone = findViewById(R.id.stphone);

        btnSave = findViewById(R.id.btnStSave);
        btnallstudent = findViewById(R.id.btnAllStudent);


        btnallstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrudOperation.this, AllStudentActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DbHelper(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = etName.getText().toString();
                String e = etEmail.getText().toString();
                String p = etPhone.getText().toString();

                Student student = new Student(0,n,e,p);

                if(dbHelper.addStudents(student)){
                    Toast.makeText(CrudOperation.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
