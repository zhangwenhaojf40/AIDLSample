package com.example.clientsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.example.aidlsample.ITestService;

public class MainActivity extends AppCompatActivity {

    ITestService iTestService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("测试*************");
        final TextView tv = findViewById(R.id.test);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tv.setText(iTestService.testAIDL("成功了"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Intent intent = new Intent("com.example.aidlsample.ITestService");
        intent.setPackage("com.example.aidlsample"); // 服务提供者的包名
        bindService(intent, connetion, Context.BIND_AUTO_CREATE);

    }


        ServiceConnection connetion = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iTestService = ITestService.Stub.asInterface(service);
                System.out.println("服务连接=="+iTestService);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }

            @Override
            public void onNullBinding(ComponentName name) {
                System.out.println("onNull=="+name.getClassName()+name.getPackageName());
            }

            @Override
            public void onBindingDied(ComponentName name) {
                System.out.println("onBindingDied=="+name.getClassName()+name.getPackageName());

            }
        };




}
