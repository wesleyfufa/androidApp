package me.wesleychuchuchan.motivator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomizeActivity extends AppCompatActivity {

    private Button enterBtn,backBtn,clearAllBtn;
    private TextView custInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        initViews();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CustomizeActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!custInput.getText().toString().equals("")){
//                    MainActivity.test.add(custInput.getText().toString());
                    MainActivity.DB.insertQuotes(custInput.getText().toString());
                    Toast.makeText(CustomizeActivity.this, "Entered", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(CustomizeActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clearAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.DB.clearAllQuotes();
                Toast.makeText(CustomizeActivity.this, "Deleted Everything Successfully", Toast.LENGTH_SHORT).show();
//                if(MainActivity.DB != null){
//                    MainActivity.DB.clearAllQuotes();
//                    Toast.makeText(CustomizeActivity.this, "Deleted Every Quotes", Toast.LENGTH_SHORT);
//                }else{
//                    Toast.makeText(CustomizeActivity.this, "Deleted Every Quotes", Toast.LENGTH_SHORT);
//                    Log.d("clickAllBtn","No Table");
//                }

            }
        });
    }
    private void initViews(){
        custInput = (TextView) findViewById(R.id.custInput);
        backBtn = (Button) findViewById(R.id.backBtn);
        enterBtn = (Button) findViewById(R.id.enterBtn);
        clearAllBtn = (Button) findViewById(R.id.clearAllBtn);
    }
}

