package com.uiciu.autonet;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.widget.Toast;

import java.util.Set;

public class ToolUtils {
    public static void enablePan(Context context,boolean enable){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bondedDevices = adapter.getBondedDevices();
        adapter.getProfileProxy(context, new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                BluetoothPan pan = (BluetoothPan) bluetoothProfile;
                for(BluetoothDevice device: bondedDevices){
                    if(enable){
                        pan.connect(device);
                        Toast.makeText(context,"蓝牙网络共享： 已打开",Toast.LENGTH_SHORT).show();
                    }else {
                        pan.disconnect(device);
                        Toast.makeText(context,"蓝牙网络共享： 已关闭",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onServiceDisconnected(int i) {

            }
        },5);
    }
}
