package ru.geekbrains.citylist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // источник данных
    private String[] cities = {"Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout list = findViewById(R.id.layout);

        // При помощи этого объекта будем доставать элементы, спрятанные в item.xml
        LayoutInflater ltInflater = getLayoutInflater();
        final Activity that = this;
        for(String city : cities){  // бежим в цикле и создаем на каждый город по элементу
            // Достаем элемент из item.xml
            View item = ltInflater.inflate(R.layout.item, list, false);
            // Находим в этом элементе TextView
            TextView cityView = item.findViewById(R.id.textView);
            // Меняем текст
            cityView.setText(city);
            cityView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(that, String.format("Выбран город - %s", ((TextView)v).getText()), Toast.LENGTH_SHORT).show();
                }
            });
            list.addView(item);
        }
    }
}
