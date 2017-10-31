package ru.geekbrains.citylist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        final Activity that = this;
        for(String city : cities){  // бежим  цикле и создаем на каждый город по элементу
            TextView cityView = new TextView(this);
            cityView.setText(city);
            cityView.setTextSize(42);
            cityView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(that, String.format("Выбран город - %s", ((TextView)v).getText()), Toast.LENGTH_SHORT).show();
                }
            });
            list.addView(cityView);
        }
    }
}
