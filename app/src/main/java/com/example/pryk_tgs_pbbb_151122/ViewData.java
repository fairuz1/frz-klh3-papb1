package com.example.pryk_tgs_pbbb_151122;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class ViewData  extends AppCompatActivity {
    TextView name, gender, address, major;
    Button modifyButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitty_view_data);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("id");
        String user_name = intent.getStringExtra("name");
        String user_gender = intent.getStringExtra("gender");
        String user_address = intent.getStringExtra("address");
        String user_major = intent.getStringExtra("major");

        name = (TextView) findViewById(R.id.viewName);
        major = (TextView) findViewById(R.id.viewMajor);
        gender = (TextView) findViewById(R.id.viewGender);
        address = (TextView) findViewById(R.id.viewAddress);
        modifyButton = (Button) findViewById(R.id.modifyData);

        name.setText(user_name);
        major.setText(user_major);
        gender.setText(user_gender);
        address.setText(user_address);

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modify_intent = new Intent(getApplicationContext(), ModifyStudentData.class);
                modify_intent.putExtra("id", user_id);
                modify_intent.putExtra("name", user_name);
                modify_intent.putExtra("major", user_major);
                modify_intent.putExtra("gender", user_gender);
                modify_intent.putExtra("address", user_address);
                startActivity(modify_intent);
            }
        });
    }
}
