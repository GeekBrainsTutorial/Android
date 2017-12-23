package ru.geekbrains.broadcastreceiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(new TestBroadcastReceiver(), new IntentFilter("ru.geekbrains.action.TestReceiver"));
    }
}
