package ru.geekbrains.webbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем представления UI
        final WebView webView = findViewById(R.id.browse);
        final TextView url = findViewById(R.id.url);
        final TextView status = findViewById(R.id.status);
        Button ok = findViewById(R.id.ok);

        // Создадим объект класса делателя запросов и налету сделаем анонимный класс слушателя
        final RequestMaker requestMaker = new RequestMaker(new RequestMaker.OnRequestListener() {
            // Обновим прогресс
            @Override
            public void onStatusProgress(String updateProgress) {
                status.setText(updateProgress);
            }
            // по окончании загрузки страницы вызовем этот метод, который и вставит текст в WebView
            @Override
            public void onComplete(String result) {
                webView.loadData(result, "text/html; charset=utf-8", "utf-8");
            }
        });

        // при нажатии на кнопку отправим запрос
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Запрос - сделать!
                requestMaker.make(url.getText().toString());
            }
        });
    }
}
