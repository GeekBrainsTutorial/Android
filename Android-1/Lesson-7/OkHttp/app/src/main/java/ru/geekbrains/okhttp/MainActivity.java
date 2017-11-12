package ru.geekbrains.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получим наш элемент WebView
        final WebView page = findViewById(R.id.page);

        // создадим запрос при помощи нашего класса Requester, параметром зададим анонимный класс
        // с обратным вызовом по завершении работы (этот вариант хоть и находится в потоке UI,
        // но все равно сделаем через обратный вызов, потому как нам в дальнейшем придется
        // запускать эту задачу в фоне)
        OkHttpRequester requester = new OkHttpRequester(new OkHttpRequester.OnResponseCompleted() {
            // Это будет вызываться по завершении закачки страницы
            @Override
            public void onCompleted(String content) {
                page.loadData(content, "text/html; charset=utf-8", "utf-8");
            }
        });
        // Запустить запрос
        requester.run("https://geekbrains.ru"); // загрузим нашу страницу
    }
}
