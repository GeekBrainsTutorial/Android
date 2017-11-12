package ru.geekbrains.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText key1;              // первый ключ
    EditText value1;            // значение первого ключа
    EditText key2;              // второй ключ
    EditText value2;            // значение второго ключа
    EditText preferenceName;    // имя файла настроек
    Button savePrefs;           // кнопка - сохранить с именем по умолчанию
    Button saveSharedPrefs;     // кнопка - сохранить по имени файла
    Button loadPrefs;           // кнопка - загрузить с именем файла по умолчанию
    Button loadSharedPrefs;     // кнопка - загрузить по имени файла

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        initButtonsListener();
    }

    // здесь просто добываем пользовательские элементы
    private void initialize() {
        key1 = findViewById(R.id.editKey1);
        value1 = findViewById(R.id.editValue1);
        key2 = findViewById(R.id.editKey2);
        value2 = findViewById(R.id.editValue2);
        preferenceName = findViewById(R.id.editPrefName);
        savePrefs = findViewById(R.id.savePrefs);
        saveSharedPrefs = findViewById(R.id.saveSharedPrefs);
        loadPrefs = findViewById(R.id.loadPrefs);
        loadSharedPrefs = findViewById(R.id.loadSharedPrefs);
    }

    // устанавливаем обработчики кнопокы
    private void initButtonsListener() {
        savePrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получаем файл настроек по умолчанию
                SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
                savePreferences(sharedPref);    // сохранить настройки
            }
        });
        saveSharedPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String preferenceFileName = preferenceName.getText().toString();
                // получаем файл настроек по имени файла, хранящемуся в preferenceName
                SharedPreferences sharedPref = getSharedPreferences(preferenceFileName, MODE_PRIVATE);
                savePreferences(sharedPref);    // сохранить настройки
            }
        });
        loadPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получаем файл настроек по умолчанию
                SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
                loadPreferences(sharedPref);    // загрузить настройки
            }
        });
        loadSharedPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String preferenceFileName = preferenceName.getText().toString();
                // получаем файл настроек по имени файла, хранящемуся в preferenceName
                SharedPreferences sharedPref = getSharedPreferences(preferenceFileName, MODE_PRIVATE);
                loadPreferences(sharedPref);    // загрузить настройки
            }
        });
    }

    // сохранем настройки
    private void savePreferences(SharedPreferences sharedPref){
        String[] keys = {key1.getText().toString(), key2.getText().toString()};
        String[] values = {value1.getText().toString(), value2.getText().toString()};
        // для сохранения настроек надо воспользоваться классом Editor
        SharedPreferences.Editor editor = sharedPref.edit();
        // теперь в Editor утановим значения
        editor.putString(keys[0], values[0]);
        editor.putInt(keys[1], Integer.parseInt(values[1]));
        // и сохраним файл настроек
        editor.commit();
    }

    private void loadPreferences(SharedPreferences sharedPref){
        String[] keys = {key1.getText().toString(), key2.getText().toString()};
        // для получения настроек нет необходимости в Editor, получаем их прямо из SharedPreferences
        String valueFirst = sharedPref.getString(keys[0], "value");
        Integer valueSecond = sharedPref.getInt(keys[1],0);
        value1.setText(valueFirst);
        value2.setText(valueSecond.toString());
    }
}
