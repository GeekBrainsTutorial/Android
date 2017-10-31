package ru.geekbrains.citylist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // источник данных
    private String[] cities = {"Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Это список
        ListView listView = findViewById(R.id.listCities);

        // Создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cities);

        // устанавливаем адаптер в список
        listView.setAdapter(adapter);

        final Activity that = this;
        // обработка нажатий
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(that, String.format("Выбран город - %s", ((TextView)v).getText()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
