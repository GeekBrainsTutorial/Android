package ru.geekbrains.textbrowser;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Display {
    private TextView page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем элементы макета
        page = findViewById(R.id.page);
        Button ok = findViewById(R.id.ok);
        final EditText url = findViewById(R.id.url);
        // Наш класс, делающий запросы
        final RequestMaker requestMaker = new RequestMaker(this);
        // Ставим слушателя на кнопку, будем по ней открывать сайт
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMaker.Make(url.getText().toString()); // сделать запрос
            }
        });

    }

    // методы для обмена с делателем запросов
    @Override
    public void setDisplayText(String text) {
        page.setText(text);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
