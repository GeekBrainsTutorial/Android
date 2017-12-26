package ru.geekbrains.geofences;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleApiClient googleApiClient;
    private Geofence geofence;
    private int id = 0;
    private double latitude;
    private double longitude;
    private float radius = 1000;
    private int duration = 100;
    private int PERMISSION_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputEditText txtLatitude = findViewById(R.id.textLatitude);
        final TextInputEditText txtLongitude = findViewById(R.id.textLongitude);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // возмем введенные координаты, и на их основе создадим геозону
                latitude = Double.parseDouble(txtLatitude.getText().toString());
                longitude = Double.parseDouble(txtLongitude.getText().toString());

                // Проверим на пермиссии, и если их нет, запросим у пользователя
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // создаем клиента GoogleApi
                    createGoogleApiClient();
                } else {
                    // пермиссии нет, будем запрашивать у пользователя
                    requestLocationPermissions();
                }
            }
        });

    }

    // Геозоны работают через службы Google Play
    // поэтому надо создать клиента этой службы
    // И соединится со службой
    private void createGoogleApiClient()
    {
        // создаем геозону через построитель.
        geofence = new Geofence.Builder()
                .setRequestId(String.valueOf(id))   // Здесь указывается имя геозоны (вернее это идентификатор, но он строковый)
                // типа геозоны, вход, перемещение внутри, выход
                .setTransitionTypes(GeofencingRequest.INITIAL_TRIGGER_ENTER | GeofencingRequest.INITIAL_TRIGGER_EXIT | GeofencingRequest.INITIAL_TRIGGER_DWELL)
                .setCircularRegion(latitude, longitude, radius) // Координаты геозоны
                .setExpirationDuration(Geofence.NEVER_EXPIRE)   // Геозона будет постоянной, пока не удалим геозону или приложение
                .setLoiteringDelay(duration)    // Установим вркеменную задержку в мс между событиями входа в зону и перемещения в зоне
                .build();
        // Создаем клиента службы GooglePlay
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)   // Укажем, что нам нужна геолокация
                .addConnectionCallbacks(connectionCallBack) // При установке соединения отработает этот callback
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {   // Отработает при неудаче
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("GeoFence", "connection failed listener");
                    }
                })
                .build();
        // Соединимся со службой
        googleApiClient.connect();
        Log.d("GeoFence", "connect to googleApiClient");
    }

    // Это результат запроса у пользователя пермиссии
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {   // Это та самая пермиссия, что мы запрашивали?
            if (grantResults.length == 1 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Все препоны пройдены и пермиссия дана
                createGoogleApiClient();
            }
        }
    }

    // Запрос пермиссии для геолокации
    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            // Запросим эти две пермиссии у пользователя
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    // Callback при соединении со службой GooglePlay
    private GoogleApiClient.ConnectionCallbacks connectionCallBack = new GoogleApiClient.ConnectionCallbacks() {
        @SuppressLint("MissingPermission")  // Мы уже проверили на пермиссии заранее
        @Override
        public void onConnected(@Nullable Bundle bundle) {  // Обрабатывается при соединении со службой GooglePlay
                GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
                // вешаем триггеры на вход, перемещение внутри и выход из зоны
                builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER | GeofencingRequest.INITIAL_TRIGGER_EXIT| GeofencingRequest.INITIAL_TRIGGER_DWELL);
                builder.addGeofence(geofence);  // Добавим геозону
                GeofencingRequest geoFenceRequest = builder.build();  // это запрос на добавление геозоны (параметры только что задавали, теперь строим)
                // создадим интент, при сигнале от Google Play будет вызываться этот интент, а интент настроен на запуск службы, обслуживающей всё это
                Intent geoService = new Intent(MainActivity.this, GeoFenceService.class);
                // интент будет работать через этот класс
                PendingIntent pendingIntent = PendingIntent
                        .getService(MainActivity.this, 0, geoService, PendingIntent.FLAG_UPDATE_CURRENT);
                // это клиент геозоны, собственно он и занимается вызовом нашей службы
                GeofencingClient geoClient = LocationServices.getGeofencingClient(MainActivity.this);
                geoClient.addGeofences(geoFenceRequest, pendingIntent);   // добавляем запрос запрос геозоны и указываем, какой интент будет при этом срабатывать
                Log.d("GeoFence", "add geofence");
        }
        @Override
        public void onConnectionSuspended(int i) {
        }
    };


}
