package com.example.chase.automaticwall;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {

    public Button id_submit;
    public EditText id_username, id_aadhar, id_password, id_retype_password;

    void init() {
        id_submit = (Button) findViewById(R.id.Submit);
        id_username = (EditText) findViewById(R.id.Username_email);
        id_password = (EditText) findViewById(R.id.Password);
        id_retype_password = (EditText) findViewById(R.id.retype_password);
        id_aadhar = (EditText) findViewById(R.id.Aadhar);


        id_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = id_username.getText().toString();
                String Aadhar = id_aadhar.getText().toString();
                String Password = id_password.getText().toString();
                String Retype_Password = id_retype_password.getText().toString();
                String data = null;
                if (Password.equals(Retype_Password)) {
                    try {
                        data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&";
                        data += URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(Aadhar, "UTF-8") + "&";
                        data += URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    NewRegister reg = new NewRegister();
                    try {
                        String temp = reg.execute(data, "a", "b").get();
                        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
                        Intent p = new Intent(Register.this, MainActivity.class);
                        startActivity(p);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Password don't Match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    public class NewRegister extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String text = null;
            try {
                URL url = new URL("http://ec2-35-154-124-161.ap-south-1.compute.amazonaws.com/AutomaticWallApp/register.php");
                try {
//                    Toast.makeText(getApplicationContext(), params[0], Toast.LENGTH_LONG).show();
                    //                  Toast.makeText(getApplicationContext(), params[1], Toast.LENGTH_LONG).show();
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    OutputStreamWriter ws = new OutputStreamWriter(conn.getOutputStream());
                    ws.write(params[0]);
                    ws.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        // Append server response in string
                        sb.append(line + "\n");
                    }

                    text = sb.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return "success";
        }
    }
}
