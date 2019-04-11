package ru.geekbrains.stateactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;       // Счетчик
    private TextView textCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String instanceState;
        // первый запуск?
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
        }
        else{
            instanceState = "Повторный запуск!";
        }
        // выведем, какой это запуск
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        textCounter = (TextView) findViewById(R.id.textCounter);    // Поле счетчика
        textCounter.setText(((Integer)counter).toString());         // Выводим счетчик на экран
        Button button = (Button) findViewById(R.id.button);         // Кнопка
        button.setOnClickListener(new View.OnClickListener() {      // Обработка нажатий
            @Override
            public void onClick(View v) {
                // Увеличим счетчик на 1 и выведем на экран
                counter++;
                textCounter.setText(((Integer)counter).toString());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        saveInstanceState.putInt("Counter", counter);               // Сохраняем счетчик
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        counter = saveInstanceState.getInt("Counter");              // Восстанавливаем счетчик
        textCounter.setText(((Integer)counter).toString());         // Выводим счетчик в поле
    }
}
