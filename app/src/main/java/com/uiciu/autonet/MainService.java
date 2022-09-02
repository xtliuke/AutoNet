package com.uiciu.autonet;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;

public class MainService extends Service {

    private BroadcastReceiver mReceiver = null;

    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("auto_net", getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(getApplicationContext(), "auto_net")
                    .setContentTitle("蓝牙网络连接服务")
                    .setContentText("已开启")
                    .setSmallIcon(R.drawable.icon)
                    .build();
            startForeground(666, notification);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ToolUtils.enablePan(MainService.this,true);
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToolUtils.enablePan(MainService.this,true);
                    }
                },200);
            }
        };
        registerReceiver(mReceiver,filter);
    }

    @Override
    public void onDestroy() {
        if(mReceiver!=null){
            unregisterReceiver(mReceiver);
        }
        super.onDestroy();
    }
}