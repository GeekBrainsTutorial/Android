package ru.geekbrains.stateactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
        }
        else{
            instanceState = "Повторный запуск!";
        }
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        final TextView textCounter = (TextView) findViewById(R.id.textCounter); // Текст
        final MainPresenter presenter = MainPresenter.getInstance();
        // Получить презентер
        textCounter.setText(((Integer)presenter.getCounter()).toString());
        // Выводим счетчик в поле
        Button button = (Button) findViewById(R.id.button);     // Кнопка
        button.setOnClickListener(new View.OnClickListener() {  // Обработка нажатий
            @Override
            public void onClick(View v) {
                presenter.incrementCounter();                   // Увеличиваем счетчик на единицу
                textCounter.setText(((Integer)presenter.getCounter()).toString());  // Выводим счетчик в поле
            }
        });
    }
}
