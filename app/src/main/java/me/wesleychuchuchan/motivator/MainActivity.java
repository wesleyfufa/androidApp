package me.wesleychuchuchan.motivator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // List of Quotes
    /*protected static List<String> test = new ArrayList<String>(){
        {add("My gran could do better! And she’s dead!");
        add("You’re getting your knickers in a twist! Calm down!");
        add("This is a really tough decision…’cause you’re both crap.");}};*/
    private Button msgBtn, customizeBtn;
    private TextView msg;
    protected static DBhelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        createNotificationChannel();
        setNotification();

//        getNotification();

        //Display quote
        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                msg.setText(shuffleMSG());
                Cursor res = DB.getQuotes();
                if(res.getCount()>0){
                    res.moveToFirst();
                    msg.setText(res.getString(0));
                }else{
                    Toast.makeText(MainActivity.this, "Add your quotes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //take to customize page
        customizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomizeActivity.class);
                startActivity(intent);
            }
        });

    }
    //intiate
    private void initViews(){
        msg = (TextView) findViewById(R.id.msg);
        msgBtn = (Button) findViewById(R.id.msgBtn);
        customizeBtn = (Button) findViewById(R.id.customizeBtn);
        DB = new DBhelper(this);
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "quoteReminderChannel";
            String description = "Channel for quote Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyUser", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void setNotification(){

        Intent intent = new Intent(MainActivity.this, Notification_reciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.MINUTE,30);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//        long timeAtButtonClick = System.currentTimeMillis();
//
//        long tenSecondInMillis = 1000 * 10;
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//                timeAtButtonClick+tenSecondInMillis,
//                pendingIntent);

    }
    //shuffle list of msg
    /*public String shuffleMSG(){
        Collections.shuffle(test);
        try{
            return test.get(0);
        }catch(Exception e){
            System.out.println("Error List is empty");
            return ("ERROR");
        }
    }*/

}



