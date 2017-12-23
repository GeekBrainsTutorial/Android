package ru.geekbrains.broadcastsender;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textHello = findViewById(R.id.textHello);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Отправляем бродкаст
                Intent intent = new Intent("ru.geekbrains.action.TestReceiver");
                intent.putExtra("hello", "BroadcastSender");
                sendBroadcast(intent);
            }
        });

        // принимаем ответ
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String answer = intent.getStringExtra("hello");
                textHello.setText(answer);
            }
        };
        // создаем фильтр для BroadcastReceiver
        IntentFilter intFilt = new IntentFilter("ru.geekbrains.action.TestedReceiver");
        registerReceiver(br, intFilt);
    }


}
