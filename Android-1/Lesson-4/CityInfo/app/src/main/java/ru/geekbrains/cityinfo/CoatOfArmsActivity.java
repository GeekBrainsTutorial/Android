package ru.geekbrains.cityinfo;

import android.app.Activity;
import android.os.Bundle;
import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

// Это активити для портретной реализации
public class CoatOfArmsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо это активити закрыть
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Если это активити запускается первый раз (с каждым новым гербом первый раз)
            // то перенаправим параметр фрагменту
            CoatOfArmsFragment details = new CoatOfArmsFragment();
            details.setArguments(getIntent().getExtras());
            // Добавим фрагмент на активити
            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
