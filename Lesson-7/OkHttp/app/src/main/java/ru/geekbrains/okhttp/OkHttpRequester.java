package ru.geekbrains.okhttp;

import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Здесь будем вызывать запросы
public class OkHttpRequester {
    private OkHttpClient client;            // Клиент
    private OnResponseCompleted listener;   // интерфейс с обратным вызовом

    public OkHttpRequester(OnResponseCompleted listener) {
        client = new OkHttpClient();
        this.listener = listener;
    }

    // Синхронный запрос страницы
    public void run(String url) {

        Request.Builder builder = new Request.Builder(); // создадим строителя
        builder.url(url);                                // укажем адрес сервера
        Request request = builder.build();               // построим запрос

        // работаем в основном потоке
        try (Response response = client.newCall(request).execute()) { // выполним запрос
            String answer = response.body().string();                 // возмем ответ сервера
            listener.onCompleted(answer);                             // вызовем обратный метод
        } catch (IOException e) {
            Log.e("OkHttp", e.getMessage(), e); // Если случится ошибка - логируем ее
            e.printStackTrace();
        }
    }

    // интерфейс обратного вызова, метод onCompleted вызывается по окончании загрузки страницы
    public interface OnResponseCompleted {
        void onCompleted(String content);   // будем этот метод вызывать по завершении обработки
    }
}
