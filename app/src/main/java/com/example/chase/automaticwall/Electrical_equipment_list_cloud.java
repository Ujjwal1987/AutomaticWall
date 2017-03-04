package com.example.chase.automaticwall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Electrical_equipment_list_cloud extends AppCompatActivity {

    public Socket S1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical_equipment_list_cloud);
        Bundle extras = getIntent().getExtras();
        String temp_EE1 = extras.getString("EE1");
        String temp_EE2 = extras.getString("EE2");
        String temp_EE3 = extras.getString("EE3");
        String temp_EE4 = extras.getString("EE4");
        String temp_EE5 = extras.getString("EE5");
        String temp_EE6 = extras.getString("EE6");
        String temp_EE7 = extras.getString("EE7");
        String temp_EE8 = extras.getString("EE8");
        String IPAddress = extras.getString("IPaddress");
        final String UID = extras.getString("UID");
        Socket_handler sh = extras.getParcelable("sockethandler");
        S1 = Socket_handler.getSocket();
        //Toast.makeText(getApplicationContext(),"Position: "+temp_EE5, Toast.LENGTH_LONG).show();
        EditText ET1 = (EditText) findViewById(R.id.ET1);
        EditText ET2 = (EditText) findViewById(R.id.ET2);
        EditText ET3 = (EditText) findViewById(R.id.ET3);
        EditText ET4 = (EditText) findViewById(R.id.ET4);
        EditText ET5 = (EditText) findViewById(R.id.ET5);
        EditText ET6 = (EditText) findViewById(R.id.ET6);
        EditText ET7 = (EditText) findViewById(R.id.ET7);
        EditText ET8 = (EditText) findViewById(R.id.ET8);

        ET1.setText(temp_EE1);
        ET2.setText(temp_EE2);
        ET3.setText(temp_EE3);
        ET4.setText(temp_EE4);
        ET5.setText(temp_EE5);
        ET6.setText(temp_EE6);
        ET7.setText(temp_EE7);
        ET8.setText(temp_EE8);

        Switch OnOff1 = (Switch) findViewById(R.id.SW1);
        Switch OnOff2 = (Switch) findViewById(R.id.SW2);
        Switch OnOff3 = (Switch) findViewById(R.id.SW3);
        Switch OnOff4 = (Switch) findViewById(R.id.SW4);
        Switch OnOff5 = (Switch) findViewById(R.id.SW5);
        Switch OnOff6 = (Switch) findViewById(R.id.SW6);
        Switch OnOff7 = (Switch) findViewById(R.id.SW7);
        Switch OnOff8 = (Switch) findViewById(R.id.SW8);

        IPAddress = "/ip/" + IPAddress + ":" + UID;

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
            out.println(IPAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        MyTask mytask = new MyTask();
        mytask.execute(IPAddress);*/

        OnOff1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay1" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay1" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });

        OnOff2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay2" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay2" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });

        OnOff3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay3" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay3" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });

        OnOff4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay4" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay4" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });


        OnOff5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay5" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay5" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });


        OnOff6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay6" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay6" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });


        OnOff7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay7" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay7" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });


        OnOff8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String relay = "/r/relay8" + ":" + UID;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works", Toast.LENGTH_LONG).show();
                } else {
                    String relay = "/r/relay8" + ":" + UID;
                    try {
                        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())), true);
                        out1.println(relay);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Test Works else", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

/*    public class MyTask extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... params) {
            try {
                InetAddress serveraddress = InetAddress.getByName(params[0]);
                try {
                    S1 = new Socket(serveraddress, 80);
                    //    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(S1.getOutputStream())),true);
                    //    out.println(params[0]);
                    //    S1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            return null;
        }
    }*/


}
