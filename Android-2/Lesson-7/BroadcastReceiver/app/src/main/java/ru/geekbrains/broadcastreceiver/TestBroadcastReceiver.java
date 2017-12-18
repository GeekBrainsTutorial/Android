package ru.geekbrains.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// получим бродкаст, и создадим новый
public class TestBroadcastReceiver extends BroadcastReceiver {

    // Получение бродкаста по интент-фильтру, указанному в манифесте
    @Override
    public void onReceive(Context context, Intent intent) {
        // сформируем и отправим ответ
        Intent intentSend = new Intent("ru.geekbrains.action.TestedReceiver");
        String prima = intent.getStringExtra("hello");  // тут прочитаем сообщение из бродкаста
        intentSend.putExtra("hello", String.format("Привет %s от TestBroadcastReceiver", prima));
        intentSend.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        context.sendBroadcast(intentSend);  // и пошлем новый бродкаст
    }
}
