package com.example.chase.automaticwall;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button add_new_room, control_lights;

    public void init() {

        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");
        final String UID = extras.getString("UID");


        add_new_room = (Button) findViewById(R.id.add_new_room);
        add_new_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, RoomAdd.class);
                startActivity(i);


            }
        });
        control_lights = (Button) findViewById(R.id.control_lights);
        control_lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = connManager.getActiveNetworkInfo();
                if (activenetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Intent k = new Intent(MainActivity.this, ControlLights_Cloud.class);
                    k.putExtra("username", username);
                    k.putExtra("UID", UID);
                    startActivity(k);
                }
                if (activenetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    Intent k = new Intent(MainActivity.this, ControlLights.class);
                    startActivity(k);
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
