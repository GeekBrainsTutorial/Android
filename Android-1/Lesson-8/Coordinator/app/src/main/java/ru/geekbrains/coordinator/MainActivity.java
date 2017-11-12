package ru.geekbrains.coordinator;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String TEXT_CONTENT = "В 2014 году на конференции был представлен новый подход к дизайну приложений. " +
            "Это попытка сделать единообразный интерфейс для всех приложений Google, " +
            "неважно где они работают на телефоне, планшете или компьютере. " +
            "А также для всех Андроид приложений. Данный стиль основан на размещении плоской бумаги на экране. " +
            "Бумага тонкая, плоская, но расположенная в трехмерном пространстве, с тенями, с движением. " +
            "Такую бумагу называют квантумной, или цифровой. Если происходит анимация, " +
            "то она и показывает пользователю, что происходит. Однако чрезмерная анимация не нужна, " +
            "никому не интересно ждать пару секунд, пока окно с сообщением налетается по экрану.\n";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView content = findViewById(R.id.content);
        content.setText(TEXT_CONTENT);

        FloatingActionButton fab = findViewById(R.id.fab);
        // Обработка нажатия на плавающую кнопку
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Здесь вылетит Snackbar
                Snackbar.make(view, "Вы нажали на плавающую кнопку", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // разместить меню в action bar (по версии перевода Google "строка действий")
        // если он присутствует. По мне так строка действий звучит еще запутанней, чем Action bar
        // Фактически это небольшой участок экрана со всякими кнопочками и функциями, типа поиска,
        // меню и т.д. Кстати, Option Menu переводится как меню параметров, что тоже не ахти как
        // понятно.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Управление касаниями на action bar.
        // Action bar будет автоматически управлять нажатиями на Home/Up кнопку
        // Вы это можете указать в родителььском активити в файле манифеста.
        int id = item.getItemId();

        final Activity that = this;

        switch (id){
            case R.id.action_settings:
                Snackbar.make(toolbar, "Вы выбрали пункт меню установки", Snackbar.LENGTH_LONG)
                        .setAction("Кнопка", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(that.getApplicationContext(), "Кнопка в Snackbar нажата", Toast.LENGTH_LONG).show();
                            }
                        }).show();
                return true;
            case R.id.action_preferences:
                Snackbar.make(toolbar, "Вы выбрали пункт меню настройки", Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.action_params:
                Snackbar.make(toolbar, "Вы выбрали пункт меню параметры", Snackbar.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
