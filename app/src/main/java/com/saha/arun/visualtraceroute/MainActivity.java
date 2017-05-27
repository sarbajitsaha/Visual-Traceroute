package com.saha.arun.visualtraceroute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private String dest_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);

        dest_ip = "";

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if((event!=null && event.getKeyCode()==KeyEvent.KEYCODE_ENTER)||(actionId== EditorInfo.IME_ACTION_DONE))
                {
                    dest_ip = "";
                    if(!checkDomainOrIP(input.getText().toString())){
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    String temp = input.getText().toString();
                                    if (!(temp.contains("http://") || temp.contains("https://"))){
                                        temp = "http://"+temp;
                                    }
                                    InetAddress address = InetAddress.getByName(new URL(temp).getHost());
                                    dest_ip = address.getHostAddress();
                                } catch (MalformedURLException | UnknownHostException e){
                                    Log.e("url_to_ip",e.getMessage());
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                        t.start();
                    } else {
                        dest_ip = input.getText().toString();
                    }
                    Log.d("url_to_ip",dest_ip);
                }
                return false;
            }
        });

    }

    private static boolean checkDomainOrIP(String text) {
        Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher m = p.matcher(text);
        return m.find();
    }
}
