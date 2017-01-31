package com.example.chase.automaticwall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RoomAdd extends AppCompatActivity {

    public Button submit1;
    public EditText room_name, room_IP_address;

//    public String Room_name, Room_IP_address;

    public void save_room() {
        submit1 = (Button) findViewById(R.id.submit1);
        room_name = (EditText) findViewById(R.id.Rname);
        room_IP_address = (EditText) findViewById(R.id.RIPaddress);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp1 = room_name.getText().toString();
                String temp2 = room_IP_address.getText().toString();
                FileOutputStream room_data = null;
                try {
                    room_data = openFileOutput("roomdata.txt", MODE_APPEND);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                OutputStreamWriter osw = new OutputStreamWriter(room_data);
                try {
                    osw.write(temp1);
                    osw.write(':');
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    osw.write(temp2);
                    osw.write(':');
                    osw.flush();
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent j = new Intent(RoomAdd.this, EE_add.class);
                startActivity(j);
                finish();
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_add);
        save_room();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
