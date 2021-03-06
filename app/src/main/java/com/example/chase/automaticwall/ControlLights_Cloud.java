package com.example.chase.automaticwall;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ControlLights_Cloud extends AppCompatActivity {

    public Socket S1 = null;
    private RoomListAdapter adapter;
    private List<ROOM> mRoomList;
    private Socket socket = null;
    private Socket_handler sh;


    public ControlLights_Cloud() throws IOException {
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup, menu);
    }

    public void filedel(int pos) {
        int block = 1024;
        FileInputStream fis = null;
        try {
            fis = openFileInput("roomdata.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

        try {
            isr.reset();
            fis.reset();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] room_display = roomdata.split("\n");
        String[] tempstring;
        Toast.makeText(getApplicationContext(), String.valueOf(room_display.length), Toast.LENGTH_LONG).show();
        if (room_display.length == 1) {
            deleteFile("roomdata.txt");
        } else {

            FileOutputStream temp = null;
            try {
                temp = openFileOutput("temp.txt", MODE_APPEND);
                OutputStreamWriter osw1 = new OutputStreamWriter(temp);
                for (int i = 0; i < room_display.length; i++) {
                    if (i == pos) {
                        continue;
                    } else {
                        try {
                            osw1.write(room_display[i]);
                            osw1.write('\n');
                            osw1.flush();
/*                            if (i == (room_display.length - 1)) {
                                osw1.write('\n');
                                osw1.flush();
                                osw1.close();
                                continue;
                            } else {
                                osw1.write('\n');
                                osw1.flush();
                            }*/
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    osw1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            FileInputStream fis1 = null;
            try {
                fis1 = openFileInput("temp.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            InputStreamReader isr1 = new InputStreamReader(fis1);
            char[] data1 = new char[block];
            String roomdata1 = "";
            int size1;
            try {
                while ((size1 = isr1.read(data1)) > 0) {
                    String read_data = String.copyValueOf(data1, 0, size1);
                    roomdata1 += read_data;
                    data1 = new char[block];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            deleteFile("temp.txt");
            deleteFile("roomdata.txt");

            String[] s_temp = roomdata1.split("\n");
            Toast.makeText(getApplicationContext(), String.valueOf(s_temp.length), Toast.LENGTH_LONG).show();
            FileOutputStream fos = null;
            try {
                fos = openFileOutput("roomdata.txt", MODE_APPEND);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            for (int i = 0; i < s_temp.length; i++) {
                try {
                    if (i == (s_temp.length - 1)) {
                        osw.write(s_temp[i]);
                        osw.write("\n");
                        osw.flush();
                        osw.close();
                    } else {
                        osw.write(s_temp[i]);
                        osw.write("\n");
                        osw.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case R.id.Delete:
                filedel(info.position);
                mRoomList.remove(info.position);
                adapter.notifyDataSetChanged();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void loadrooms() {
        int block = 1024;
        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");
        final String UID = extras.getString("UID");
        String data1 = "/c/" + username + ":" + UID;


        String IPaddress = "ec2-35-154-154-71.ap-south-1.compute.amazonaws.com";
        Sendmsg SM = new Sendmsg();
        SM.execute(IPaddress, data1);

//        ConnectionTest CT1 = new ConnectionTest();
//        CT1.execute("192.168.1.4");
//        String data1 = username + ":" + UID;
//        PrintWriter out1 = null;
//        try {
//            out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out1.println(data1);
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
//            Toast.makeText(getApplicationContext(), String.valueOf(room_display.length), Toast.LENGTH_LONG).show();
            for (int b = 0; b < room_display.length; b++) {
                tempstring = room_display[b].split(":");
                mRoomList.add(new ROOM(tempstring[0], tempstring[1], tempstring[2], tempstring[3], tempstring[4], tempstring[5], tempstring[6], tempstring[7], tempstring[8], tempstring[9]));
            }

            adapter = new RoomListAdapter(getApplicationContext(), mRoomList);
            lsv.setAdapter(adapter);
            registerForContextMenu(lsv);


            int count = mRoomList.size();
            final ArrayList<Integer> disabledpos = new ArrayList<Integer>();

            for (int q = 0; q < count; q++) {
                ROOM temp_room1 = mRoomList.get(q);
                String IP = temp_room1.getRoom_IP_Address();
                String online = null;
//                ConnectionTest CT = new ConnectionTest();
/*                try {
                    online = CT.execute(IP, "a", "b").get();
                    Boolean b1 = Boolean.valueOf(online);

                    if (!b1) {
                        disabledpos.add(q);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }*/

            }
            try {
                isr.reset();
                fis.reset();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int temp_position = position;
                    Intent p = new Intent(ControlLights_Cloud.this, Electrical_equipment_list_cloud.class);
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
                        p.putExtra("UID", UID);
                        p.putExtra("IPaddress", temp_room.getRoom_IP_Address());
                        p.putExtra("sockethandler", (Serializable) sh);
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
        setContentView(R.layout.activity_control_lights__cloud);
        loadrooms();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


/*    public class ConnectionTest extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                InetAddress serveraddress = InetAddress.getByName(params[0]);
                try {
                    S1 = new Socket(serveraddress, 8888);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/

    public class Sendmsg extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
//            Socket socket = null;
            InetAddress ipaddress = null;
            try {
                ipaddress = InetAddress.getByName(params[0]);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                socket = new Socket(ipaddress, 8888);
                Socket_handler.setSocket(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                PrintWriter out1;
                out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                out1.println(params[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

//        @Override
//        protected void onPostExecute(String s) {
//            Toast.makeText(getApplicationContext(), String.valueOf(s), Toast.LENGTH_LONG).show();
//            super.onPostExecute(s);
//        }

/*        private Boolean returnfunc(Boolean s1){
            Toast.makeText(getApplicationContext(), String.valueOf(s1), Toast.LENGTH_LONG).show();
            return s1;
        }*/


    }
}
