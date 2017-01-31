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

public class EE_add extends AppCompatActivity {

    public Button submit2;
    public EditText EE_1, EE_2, EE_3, EE_4, EE_5, EE_6, EE_7, EE_8;

    public void save_EE() {
        submit2 = (Button) findViewById(R.id.submit2);

        EE_1 = (EditText) findViewById(R.id.Electrical_E1);
        EE_2 = (EditText) findViewById(R.id.Electrical_E2);
        EE_3 = (EditText) findViewById(R.id.Electrical_E3);
        EE_4 = (EditText) findViewById(R.id.Electrical_E4);
        EE_5 = (EditText) findViewById(R.id.Electrical_E5);
        EE_6 = (EditText) findViewById(R.id.Electrical_E6);
        EE_7 = (EditText) findViewById(R.id.Electrical_E7);
        EE_8 = (EditText) findViewById(R.id.Electrical_E8);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                String temp1 = EE_1.getText().toString();
                String temp2 = EE_2.getText().toString();
                String temp3 = EE_3.getText().toString();
                String temp4 = EE_4.getText().toString();
                String temp5 = EE_5.getText().toString();
                String temp6 = EE_6.getText().toString();
                String temp7 = EE_7.getText().toString();
                String temp8 = EE_8.getText().toString();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    osw.write(temp3);
                    osw.write(':');
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    osw.write(temp4);
                    osw.write(':');
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    osw.write(temp5);
                    osw.write(':');
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    osw.write(temp6);
                    osw.write(':');
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    osw.write(temp7);
                    osw.write(':');
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    osw.write(temp8);
                    osw.write('\n');
                    osw.flush();
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent l = new Intent(EE_add.this, MainActivity.class);
                startActivity(l);
                finish();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ee_add);
        save_EE();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
