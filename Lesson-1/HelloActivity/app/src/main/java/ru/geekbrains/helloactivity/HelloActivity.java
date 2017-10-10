package ru.geekbrains.helloactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {  // Наследуем класс Activity
    // или его потомка
    // Этот метод вызывается при создании Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        TextView greeting = (TextView) findViewById(R.id.greeting); // Получить элемент
        BuilderGreetingPhrase builderGreetingPhrase = new BuilderGreetingPhrase(getResources());
        greeting.setText(builderGreetingPhrase.get());                            // Заменить значение
    }
}
