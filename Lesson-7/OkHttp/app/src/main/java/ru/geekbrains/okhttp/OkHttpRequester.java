package ru.geekbrains.okhttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static junit.framework.Assert.fail;

// Здесь будем вызывать запросы
public class OkHttpRequester {
    private OnResponseCompleted listener;   // интерфейс с обратным вызовом

    public OkHttpRequester(OnResponseCompleted listener) {
        this.listener = listener;
    }

    // Синхронный запрос страницы
    public void run(String url) {
        OkHttpClient client = new OkHttpClient();        // Клиент
        Request.Builder builder = new Request.Builder(); // создадим строителя
        builder.url(url);                                // укажем адрес сервера
        Request request = builder.build();               // построим запрос

        Call call = client.newCall(request);            // Ставим запрос в очередь
        call.enqueue(new Callback() {

            // Это срабатывает по приходу ответа от сервера
            public void onResponse(Call call, Response response)
                    throws IOException {
                String answer = response.body().string();
                listener.onCompleted(answer);               // вызовем наш метод обратного вызова
            }

            // При сбое
            public void onFailure(Call call, IOException e) {
                fail();
            }
        });
    }

    // интерфейс обратного вызова, метод onCompleted вызывается по окончании загрузки страницы
    public interface OnResponseCompleted {
        void onCompleted(String content);
    }
}