package com.example.pryk_tgs_pppb1_database1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addButton;
    StudentAdapter newAdapter;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    ArrayList<UserData> studentArrayList;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddStudentData.class));
            }
        });

        recycler = (RecyclerView) findViewById(R.id.dataCollection);
        RecyclerView.LayoutManager recyclerLayout = new LinearLayoutManager(this);
        recycler.setLayoutManager(recyclerLayout);
        recycler.setItemAnimator(new DefaultItemAnimator());

        viewData();
    }

    public void viewData() {
        db.child("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentArrayList = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    UserData newStudent = item.getValue(UserData.class);
                    // newStudent.setKey();
                    studentArrayList.add(newStudent);
                }

                newAdapter = new StudentAdapter(studentArrayList, MainActivity.this);
                recycler.setAdapter(newAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}