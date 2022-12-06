package com.example.pryk_tgs_pbbb_151122;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.MAJOR, DatabaseHelper.GENDER, DatabaseHelper.ADDRESS };
    final int[] to = new int[] { R.id.id, R.id.name, R.id.major, R.id.gender, R.id.address };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView student_id = (TextView) view.findViewById(R.id.id);
                TextView student_name = (TextView) view.findViewById(R.id.name);
                TextView student_major = (TextView) view.findViewById(R.id.major);
                TextView student_gender = (TextView) view.findViewById(R.id.gender);
                TextView student_address = (TextView) view.findViewById(R.id.address);

                String id = student_id.getText().toString();
                String name = student_name.getText().toString();
                String major = student_major.getText().toString();
                String gender = student_gender.getText().toString();
                String address = student_address.getText().toString();

                Intent view_intent = new Intent(getApplicationContext(), ViewData.class);
                view_intent.putExtra("id", id);
                view_intent.putExtra("name", name);
                view_intent.putExtra("major", major);
                view_intent.putExtra("gender", gender);
                view_intent.putExtra("address", address);

                startActivity(view_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add = new Intent(this, AddStudentData.class);
            startActivity(add);
        }
        return super.onOptionsItemSelected(item);
    }
}