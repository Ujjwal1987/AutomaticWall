package com.example.chase.automaticwall;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ControlLights extends AppCompatActivity {

    private RoomListAdapter adapter;
    private List<ROOM> mRoomList;

    public void loadrooms() {

        int block = 1024;

        try {
            FileInputStream fis = openFileInput("roomdata.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] data = new char[block];
            String roomdata = "";
            int size;
            try {
                while ((size = isr.read(data)) > 0) {
                    String read_data = String.copyValueOf(data, 0, size);
                    roomdata += read_data;
                    data = new char[block];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] room_display = roomdata.split("\n");
            String[] tempstring;
            ListView lsv = (ListView) findViewById(R.id.Room_list);
            mRoomList = new ArrayList<>();
            for (int b = 0; b < room_display.length; b++) {
                tempstring = room_display[b].split(":");
                mRoomList.add(new ROOM(tempstring[0], tempstring[1], tempstring[2], tempstring[3], tempstring[4], tempstring[5], tempstring[6], tempstring[7], tempstring[8], tempstring[9]));
            }

            adapter = new RoomListAdapter(getApplicationContext(), mRoomList);
            lsv.setAdapter(adapter);

            int count = mRoomList.size();
            final ArrayList<Integer> disabledpos = new ArrayList<Integer>();

            for (int q = 0; q < count; q++) {
                ROOM temp_room1 = mRoomList.get(q);
                String IP = temp_room1.getRoom_IP_Address();
                String online = null;
                ConnectionTest CT = new ConnectionTest();
                try {
                    online = CT.execute(IP, "a", "b").get();
                    Boolean b1 = Boolean.valueOf(online);
                    Toast.makeText(getApplicationContext(), online, Toast.LENGTH_SHORT).show();
                    if (!b1) {
                        disabledpos.add(q);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

            lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int temp_position = position;
                    Intent p = new Intent(ControlLights.this, Electrical_equipment_list.class);
                    if (!(disabledpos.contains(position))) {
                        ROOM temp_room = mRoomList.get(temp_position);
                        p.putExtra("EE1", temp_room.getEE1());
                        p.putExtra("EE2", temp_room.getEE2());
                        p.putExtra("EE3", temp_room.getEE3());
                        p.putExtra("EE4", temp_room.getEE4());
                        p.putExtra("EE5", temp_room.getEE5());
                        p.putExtra("EE6", temp_room.getEE6());
                        p.putExtra("EE7", temp_room.getEE7());
                        p.putExtra("EE8", temp_room.getEE8());
                        p.putExtra("IPaddress", temp_room.getRoom_IP_Address());
                        startActivity(p);
                    }
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_lights);
        loadrooms();
    }

    public class ConnectionTest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            Boolean online;
            String tempstring = null;
            try {
                InetAddress roomaddress = InetAddress.getByName(params[0]);
                online = roomaddress.isReachable(100);
                tempstring = String.valueOf(online);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempstring;
        }

        @Override
        protected void onPostExecute(String s) {
//            Toast.makeText(getApplicationContext(), String.valueOf(s), Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }

/*        private Boolean returnfunc(Boolean s1){
            Toast.makeText(getApplicationContext(), String.valueOf(s1), Toast.LENGTH_LONG).show();
            return s1;
        }*/


    }

}
