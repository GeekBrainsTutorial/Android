package ru.geekbrains.okhttp;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Здесь будем вызывать запросы
public class OkHttpRequester {
    private OnResponseCompleted listener;   // интерфейс с обратным вызовом

    public OkHttpRequester(OnResponseCompleted listener) {
        this.listener = listener;
    }

    // Синхронный запрос страницы
    public void run(String url) {
        Requester requester = new Requester(listener);
        requester.execute(url);
    }

    // интерфейс обратного вызова, метод onCompleted вызывается по окончании загрузки страницы
    public interface OnResponseCompleted {
        void onCompleted(String content);
    }

    // Асинхронная задача
    static class Requester extends AsyncTask<String, Void, String>{
        OnResponseCompleted listener;   // слушатель

        Requester(OnResponseCompleted listener){
            this.listener = listener;
        }

        // Это выполняется в фоновом потоке
        @Override
        protected String doInBackground(String... url) {
            return getContent(url[0]);
        }

        // Это выполняется по завершении работы в потоке UI
        @Override
        protected void onPostExecute(String content){
            listener.onCompleted(content);      // Вызовем обратный вызов
        }

        // получение данных при отправке запроса
        private String getContent(String url){
            OkHttpClient client = new OkHttpClient();        // Клиент
            Request.Builder builder = new Request.Builder(); // создадим строителя
            builder.url(url);                                // укажем адрес сервера
            Request request = builder.build();               // построим запрос

            try (Response response = client.newCall(request).execute()) { // выполним запрос
                return response.body().string();                          // возмем ответ сервера
            } catch (IOException e) {
                Log.e("OkHttp", e.getMessage(), e);     // при ошибке - логируем
                e.printStackTrace();
            }
            return "";
        }
    }
}