package ru.geekbrains.helloactivity;

import android.content.res.Resources;
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
        greeting.setText(greetPhrase());                            // Заменить значение
    }

    private String greetPhrase(){
        TextView greeting = (TextView) findViewById(R.id.greeting); // Получить элемент
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // час сейчас
        Resources resources = getResources();
        String helloer = resources.getString(R.string.helloer);
        if (5 <= currentHour && currentHour < 12 ){         // Если утро
            return String.format("%s %s!", resources.getString(R.string.morning), helloer);
        }
        else if (12 <= currentHour && currentHour < 6){     // Если день
            return String.format("%s %s!", resources.getString(R.string.afternoon), helloer);
        }
        else if (6 <= currentHour && currentHour < 9){      // Если вечер
            return String.format("%s %s!", resources.getString(R.string.evening), helloer);
        }
        else {                                              // Если поздний вечер или ночь
            return String.format("%s %s!", resources.getString(R.string.night), helloer);
        }
    }
}