package com.uiciu.autonet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this,MainService.class));
    }

    public void doOpen(View view) {
        ToolUtils.enablePan(MainActivity.this,true);
    }

    public void doClose(View view) {
        ToolUtils.enablePan(MainActivity.this,false);
    }
}