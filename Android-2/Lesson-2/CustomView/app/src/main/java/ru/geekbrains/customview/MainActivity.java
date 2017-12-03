package ru.geekbrains.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Программно создадим макет
        LinearLayout layout = new LinearLayout(this);
        // сконструируем кастомный элемент
        CustomView customView = new CustomView(this);
        //Добавим элемент на макет
        Log.d("CustomView", "addView");
        layout.addView(customView);
        // Привяжем макет к активити
        setContentView(layout);
    }
}
