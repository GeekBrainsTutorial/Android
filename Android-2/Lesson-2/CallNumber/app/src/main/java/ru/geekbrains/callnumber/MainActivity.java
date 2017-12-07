package ru.geekbrains.callnumber;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_CODE = 10; // Этот код будет возворащаться, когда пользователь согласится
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.phoneNumber);
        Button callPhone = findViewById(R.id.callPhone);
        final Context that = this;
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(phoneNumber.getText().toString()); // Создадим вызов
            }
        });
    }

    // Делаем вызов
    void makeCall(String number) {
        String intentNumber = "tel:" + number;
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(intentNumber));
        // Если пермиссии есть, то просто вызовем активити с номером для вызова
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            // Если пермиссии нет, то запросим у пользователя
            requestForCallPermission();
        }
    }

    // Запрос пермиссии для вызова
    public void requestForCallPermission() {
        // Можем ли мы запрашивать пермиссии, если нет, то и смысла нет запрашивать
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            // Запрашиваем пермиссии у пользователя
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
        }
    }

    // Это результат запроса у пользователя пермиссии
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {   // Это та самая пермиссия, что мы запрашивали?
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Все препоны пройдены и пермиссия дана, можно делать звонок
                makeCall(phoneNumber.getText().toString());
            }
        }
    }

}
