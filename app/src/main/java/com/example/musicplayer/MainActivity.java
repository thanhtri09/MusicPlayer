package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;
    ImageButton  btnplay,btnstop,btndowntime,btnnextime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnplay = findViewById(R.id.btnPlay);
        btnstop = findViewById(R.id.btnStop);
        btndowntime = findViewById(R.id.btnDownTime);
        btnnextime = findViewById(R.id.btnNextTime);
    connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
    final Intent intent = new Intent(MainActivity.this,MyService.class);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, connection,
                        Context.BIND_AUTO_CREATE);
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound){
                    unbindService(connection);
                    isBound = true;
                }
            }
        });
        btnnextime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound){
                    myService.fastForward();
                }
            }
        });
        btndowntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound){
                    myService.downtime();
                }
            }
        });
    }
}