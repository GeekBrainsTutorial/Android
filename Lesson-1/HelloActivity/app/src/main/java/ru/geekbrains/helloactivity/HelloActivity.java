
package ru.geekbrains.helloactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class HelloActivity extends AppCompatActivity {  // Наследуем класс Activity
    // или его потомка
    // Этот метод вызывается при создании Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        TextView greeting = (TextView) findViewById(R.id.greeting); // Получить элемент
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // час сейчас
        if (5 <= currentHour && currentHour < 12 ){         // Если утро
            greeting.setText("Good morning GeekBrains!");
        }
        else if (12 <= currentHour && currentHour < 6){     // Если день
            greeting.setText("Good afternoon GeekBrains!");
        }
        else if (6 <= currentHour && currentHour < 9){      // Если вечер
            greeting.setText("Good evening GeekBrains!");
        }
        else {
            greeting.setText("Good night GeekBrains!");     // Если поздний вечер или ночь
        }
    }
}
