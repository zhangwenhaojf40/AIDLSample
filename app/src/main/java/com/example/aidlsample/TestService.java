package com.example.aidlsample;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Imp();
    }
    class Imp extends ITestService.Stub{//集成binder并实现编写的接口

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String  testAIDL(String content) throws RemoteException {
            return "测试AIDL:"+content;
        }
    }
}
