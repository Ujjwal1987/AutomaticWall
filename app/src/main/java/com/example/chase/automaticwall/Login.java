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


public class Login extends AppCompatActivity {

    public Button id_login, id_register, id_offline;
    public EditText id_username, id_password;

    public void main() {
        id_login = (Button) findViewById(R.id.Login);
        id_register = (Button) findViewById(R.id.register);
        id_username = (EditText) findViewById(R.id.editText_username);
        id_password = (EditText) findViewById(R.id.editText_password);
        id_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = id_username.getText().toString();
                String password = id_password.getText().toString();
                String data = null;
                try {
                    data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&";
                    data += URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Webconnection wc = new Webconnection();
                try {
                    String temp = wc.execute(data, "a", "b").get();
                    Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
                    temp = temp.substring(0, 1);
                    int i = Integer.parseInt(temp);
                    Toast.makeText(getApplicationContext(), String.valueOf(i), Toast.LENGTH_LONG).show();
                    if (temp.equals("1")) {
                        Intent p = new Intent(Login.this, MainActivity.class);
                        startActivity(p);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Login Psssword", Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        id_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Login.this, Register.class);
                startActivity(p);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        main();
    }

    public class Webconnection extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String text = null;
            try {
                URL url = new URL("http://192.168.1.2/AutomaticWallApp/index.php");
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
//                    String data = URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(params[0],"UTF-8");
//                    data += URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");
//                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
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
//                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return text;
        }
    }
}
