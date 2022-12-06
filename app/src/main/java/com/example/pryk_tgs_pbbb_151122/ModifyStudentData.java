package com.example.pryk_tgs_pbbb_151122;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
public class ModifyStudentData extends AppCompatActivity implements View.OnClickListener {
    EditText id, name, major, gender, address;
    Button deleteButton, updateButton;
    private DBManager dbManager;
    private long _id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify student record");
        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        id = (EditText) findViewById(R.id.studentId);
        name = (EditText) findViewById(R.id.studentName);
        major = (EditText) findViewById(R.id.studentMajor);
        gender = (EditText) findViewById(R.id.studentGender);
        address = (EditText) findViewById(R.id.studentAddreas);
        deleteButton = (Button) findViewById(R.id.btn_delete);
        updateButton = (Button) findViewById(R.id.btn_update);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("id");
        String user_name = intent.getStringExtra("name");
        String user_gender = intent.getStringExtra("gender");
        String user_address = intent.getStringExtra("address");
        String user_major = intent.getStringExtra("major");

        _id = Long.parseLong(user_id);
        id.setText(user_id);
        name.setText(user_name);
        gender.setText(user_gender);
        address.setText(user_address);
        major.setText(user_major);

        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                Log.d("updateButton", "button is clicked");
                String student_name = name.getText().toString();
                String student_gender = gender.getText().toString();
                String student_address = address.getText().toString();
                String student_major = major.getText().toString();

                dbManager.update(_id, student_name, student_major, student_gender, student_address);
                this.returnHome();
                break;

            case R.id.btn_delete:
                Log.d("updateButton", "button is clicked");
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
