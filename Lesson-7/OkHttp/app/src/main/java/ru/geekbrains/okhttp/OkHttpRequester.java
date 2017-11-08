package ru.geekbrains.okhttp;

import android.os.Handler;
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
            // этот хандлер нужен для синхронизации потоков, если его сконструировать
            // он запомнит поток и в дальнейшем будет с ним синхронизироваться
            final Handler handler = new Handler();

            // Это срабатывает по приходу ответа от сервера
            public void onResponse(Call call, Response response)
                    throws IOException {
                final String answer = response.body().string();
                // синхронизируем поток с потоком UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onCompleted(answer); // вызовем наш метод обратного вызова
                    }
                });
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