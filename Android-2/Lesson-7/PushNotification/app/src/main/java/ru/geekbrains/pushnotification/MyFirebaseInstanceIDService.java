package ru.geekbrains.pushnotification;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

// Получение ключа установки
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private final String TAG = "PushIDService";

    @Override
    public void onTokenRefresh() {
        // Получить ключ установки приложения на устройство.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // Здесь надо связать этот ключ с данными пользователя в базе данных
        // по этому ключу можно будет идентифицировать устройство
        // и отсылать сообщения определенному польщователю.
        // Предполагается, что у вас будет где-то хранится база данных с этими данными
        sendRegistrationToServer(refreshedToken);
    }

    // Метод отправки ключа в вашу БД
    private void sendRegistrationToServer(String refreshedToken){

    }

}
