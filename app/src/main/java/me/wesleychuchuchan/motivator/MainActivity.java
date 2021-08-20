package me.wesleychuchuchan.motivator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

//    private String test[] = "human garbage", "shit face", "C***";

    protected static List<String> test = new ArrayList<String>(){
        {add("My gran could do better! And she’s dead!");
        add("You’re getting your knickers in a twist! Calm down!");
        add("This is a really tough decision…’cause you’re both crap.");}};
    private Button msgBtn, customizeBtn;
    private TextView msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        //Daliy notification
        //ERROR have to have API 25 and lower
//        getNotification();

        //Display quote
        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText(shuffleMSG());
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
    private void getNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myNotification", "myNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,20);
        calendar.set(calendar.SECOND,2);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "myNotification");
         builder.setContentTitle("Notification tiltle");
         builder.setContentText("notificaotn text");
         builder.setSmallIcon(R.drawable.ic_launcher_background);
         builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
        managerCompat.notify(1,builder.build());

//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, );

//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(Calendar.HOUR_OF_DAY,23);
//        calendar.set(Calendar.MINUTE,20);
//        calendar.set(calendar.SECOND,2);
//
//        Intent intent = new Intent(getApplicationContext(),Notification_reciever.class);
//        intent.setAction("myNotificationMessage");
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }
    private void initViews(){
        msg = (TextView) findViewById(R.id.msg);
        msgBtn = (Button) findViewById(R.id.msgBtn);
        customizeBtn = (Button) findViewById(R.id.customizeBtn);
    }
    public String shuffleMSG(){
        Collections.shuffle(test);
        try{
            return test.get(0);
        }catch(Exception e){
            System.out.println("Error List is empty");
            return ("ERROR");
        }
    }

}



