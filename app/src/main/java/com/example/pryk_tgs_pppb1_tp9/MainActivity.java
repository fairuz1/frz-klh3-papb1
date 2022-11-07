package com.example.pryk_tgs_pppb1_tp9;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//public class MainActivity extends AppCompatActivity {
//    private NotificationManager mNotifyManager;
//    private static final int NOTIFICATION_ID = 0;
//    public String CHANNEL_ID = "0.0.0";
//    Button notifyButton;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        notifyButton = (Button) findViewById(R.id.notify);
//        notifyButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                sendNotification();
//            }
//        });
//        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//    }
//
//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "My channel 1";
//            String description = "Channel for notification test";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }
//
//    public void sendNotification(){
//        int notificationId = 0;
//        createNotificationChannel();
//        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_android)
//                .setContentText("This is your notification text.")
//                .setContentTitle("You've been notified!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        // notificationId is a unique int for each notification that you must define
//        notificationManager.notify(notificationId, notifyBuilder.build());
//    }
//}
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    SharedPreferences sharedpreferences;
    EditText name, email;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
    }

    public void Save(View view) {
        String n = name.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.commit();
    }

    public void clear(View view) {
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        name.setText("");
        email.setText("");
    }

    public void Get(View view) {
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}