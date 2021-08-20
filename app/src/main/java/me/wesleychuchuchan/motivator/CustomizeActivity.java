package me.wesleychuchuchan.motivator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomizeActivity extends AppCompatActivity {

    private Button custBtn,backBtn;
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
        custBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!custInput.getText().toString().equals("")){
                    MainActivity.test.add(custInput.getText().toString());
                    Toast.makeText(CustomizeActivity.this, "Entered", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(CustomizeActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void initViews(){
        custInput = (TextView) findViewById(R.id.custInput);
        backBtn = (Button) findViewById(R.id.backBtn);
        custBtn = (Button) findViewById(R.id.custBtn);
    }
}

