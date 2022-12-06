package com.example.pryk_tgs_pbbb_151122;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
public class AddStudentData extends AppCompatActivity implements View.OnClickListener {
    EditText id, name, major, gender, address;
    Button addButton;
    private DBManager dbManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("add student record");
        setContentView(R.layout.activity_add_record);

        id = (EditText) findViewById(R.id.studentId);
        name = (EditText) findViewById(R.id.studentName);
        major = (EditText) findViewById(R.id.studentMajor);
        gender = (EditText) findViewById(R.id.studentGender);
        address = (EditText) findViewById(R.id.studentAddreas);
        addButton = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:
                final String user_id = id.getText().toString();
                final String user_name = name.getText().toString();
                final String user_gender = gender.getText().toString();
                final String user_address = address.getText().toString();
                final String user_major = major.getText().toString();

                dbManager.insert(user_id, user_name, user_major, user_gender, user_address);

                Intent main = new Intent(AddStudentData.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
