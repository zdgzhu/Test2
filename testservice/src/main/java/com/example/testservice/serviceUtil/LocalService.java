package com.example.testservice.serviceUtil;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LocalService  extends Service{


    private final LocalBinder mBinder = new LocalBinder();
    private final Random random = new Random();

    public class LocalBinder extends Binder{
        //返回此对象就可以
      public   LocalService getService() {
            return LocalService.this;
        }
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public int getRandomNumber() {
        return random.nextInt(100);
    }



}
