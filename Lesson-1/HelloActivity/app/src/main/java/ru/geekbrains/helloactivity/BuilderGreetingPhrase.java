package ru.geekbrains.helloactivity;

import android.content.res.Resources;
import java.util.Calendar;

// Построитель фразы приветствия
public class BuilderGreetingPhrase {
    private int currentHour;        // Текущий час
    private Resources resources;    // Ресурсы

    // Конструктор, здесь передадим ресурсы из активити и получим текущий час
    public BuilderGreetingPhrase(Resources resources){
        currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        this.resources = resources;
    }

    // Определение, какую-же строку надо сформировать
    public String get(){
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
