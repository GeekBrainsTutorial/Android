package ru.geekbrains.geofences;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

// Сервис обработки событий происходящих в геозоне
public class GeoFenceService extends IntentService {

    private int messageId = 0;

    public GeoFenceService() {
        super("GeoFenceService");
        Log.d("GeoFence", "constructor");
    }

    @Override
    public void onCreate() {
        Log.d("GeoFence", "on create");
        super.onCreate();
    }

    public GeoFenceService(String name) {
        super(name);
        Log.d("GeoFence", "constructor");
    }

    // именно здесь будет обработка события
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("GeoFence", "on handle");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);   // получаем событие
        int transitionType = geofencingEvent.getGeofenceTransition();   // определяем тип события
        // List<Geofence> triggeredGeofences = geofencingEvent.getTriggeringGeofences();    // если надо, получаем, какие геозоны нам подходят
        notifyGeofence(transitionType); // отправляем уведомление
    }

    // Отпрапвка уведомлений
    private void notifyGeofence(int transitionType) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(getTransitionTypeString(transitionType));
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, notificationBuilder.build());
    }

    // возвращает строку с типом события
    private String getTransitionTypeString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "enter";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "exit";
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                return "dwell";
            default:
                return "unknown";
        }
    }
}
