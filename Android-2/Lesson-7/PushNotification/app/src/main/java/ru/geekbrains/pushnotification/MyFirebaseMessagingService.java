package ru.geekbrains.pushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

// Получение уведомлений от Firebase
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String TAG = "PushMessageService";
    int messageId = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Если здесь не создавать уведомления, то при активном приложении они не будут создаваться
        // В данном случае они создаются всегда.
        makeNote(this, remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

        // Оставим здесь отладочную информацию
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    // вывод уведомления в строке состояния
    private void makeNote(Context context, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(String.format("Push [%s]", title))
                .setContentText(message);
        // Сделаем так, чтобы при клике на уведомление приложение не только открылось,
        // но и заполнились бы данные в активити.
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("title",  title);
        resultIntent.putExtra("body",  message);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }

}
