package com.example.pryk_tgs_pppb1_database1;
import android.content.Intent;
import com.example.pryk_tgs_pppb1_database1.UserData;
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
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentData extends AppCompatActivity {
    EditText name, subject, email, icons;
    Button addDataButton, returnPageButton;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_data);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        name = (EditText) findViewById(R.id.name);
        subject = (EditText) findViewById(R.id.subject);
        email = (EditText) findViewById(R.id.email);
        icons = (EditText) findViewById(R.id.icon);
        returnPageButton = (Button) findViewById(R.id.returnButton);
        addDataButton = (Button) findViewById(R.id.addDataButton);

        returnPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("back button", "button is clicked");
                startActivity(new Intent(AddStudentData.this, MainActivity.class));
            }
        });

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty()) {
                    name.setError("Please enter your name");
                } else if (subject.getText().toString().isEmpty()) {
                    subject.setError("Please enter the subject");
                } else if (email.getText().toString().isEmpty()) {
                    subject.setError("Please enter your email");
                } else {
                    String studentKey = db.child("students").push().getKey();
                    //db.child("students").child().child(studentKey).setValue(new UserData(name.getText().toString(), email.getText().toString(), icons.getText().toString(), subject.getText().toString(), studentKey));
                    db.child("students").child(studentKey).setValue(new UserData(name.getText().toString(), email.getText().toString(), icons.getText().toString(), subject.getText().toString(), studentKey)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("added document id", studentKey);
                            Toast.makeText(AddStudentData.this, "Data has been saved", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddStudentData.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddStudentData.this, "An Error Has Occurred, Data is Not Saved!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
