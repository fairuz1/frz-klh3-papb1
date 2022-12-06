package com.example.pryk_tgs_pppb1_database1;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditStudentData extends AppCompatActivity {
    String student_name, student_subject, student_email, student_image, student_id;
    EditText name, subject, email, icons;
    Button editData;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_data);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Intent intent = getIntent();
        student_name = intent.getStringExtra("name");
        student_subject = intent.getStringExtra("subject");
        student_email = intent.getStringExtra("mail");
        student_image = intent.getStringExtra("icons");
        student_id = intent.getStringExtra("id");

        Log.d("name", student_name);
        Log.d("name", student_subject);
        Log.d("name", student_email);
        Log.d("name", student_image);
        Log.d("name", student_id);

        name = (EditText) findViewById(R.id.name);
        subject = (EditText) findViewById(R.id.subject);
        email = (EditText) findViewById(R.id.email);
        icons = (EditText) findViewById(R.id.icon);
        editData = (Button) findViewById(R.id.addDataButton);

        editData.setText("edit data");
        name.setText(student_name);
        subject.setText(student_subject);
        email.setText(student_email);
        icons.setText(student_image);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.child("students").child(student_id).setValue(new UserData(name.getText().toString(), email.getText().toString(), icons.getText().toString(), subject.getText().toString(), student_id)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditStudentData.this, "Data has been saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditStudentData.this, MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditStudentData.this, "An Error Has Occurred, Data is Not Saved!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
