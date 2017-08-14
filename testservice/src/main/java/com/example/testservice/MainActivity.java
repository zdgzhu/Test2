package com.example.testservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testservice.serviceUtil.LocalService;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button btnService;
    private Button startSer;
    private LocalService.LocalBinder mBinder;
    private LocalService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        initView();
        
    }

    private void initView() {
        btnService = (Button) findViewById(R.id.btn_service_number);
        startSer = (Button) findViewById(R.id.startSer);
        btnService.setOnClickListener(this);
        startSer.setOnClickListener(this);

        
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.startSer:
                Intent intent = new Intent(MainActivity.this, LocalService.class);
                bindService(intent, connect, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_service_number:
                int num=mService.getRandomNumber();
                Toast.makeText(this, "btn_service_number = "+num, Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connect);
    }

    private ServiceConnection connect=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (LocalService.LocalBinder) service;
            mService = mBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    
}
