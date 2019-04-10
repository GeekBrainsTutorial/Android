package ru.geekbrains.coordinator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String TEXT_CONTENT = "В 2014 году на конференции был представлен новый подход к дизайну приложений. " +
            "Это попытка сделать единообразный интерфейс для всех приложений Google, " +
            "неважно где они работают на телефоне, планшете или компьютере. " +
            "А также для всех Андроид приложений. Данный стиль основан на размещении плоской бумаги на экране. " +
            "Бумага тонкая, плоская, но расположенная в трехмерном пространстве, с тенями, с движением. " +
            "Такую бумагу называют квантумной, или цифровой. Если происходит анимация, " +
            "то она и показывает пользователю, что происходит. Однако чрезмерная анимация не нужна, " +
            "никому не интересно ждать пару секунд, пока окно с сообщением налетается по экрану.\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Установка Action Bar. В данном случае, это наш ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // покажем на экране длинный текст для скроллинга
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
        // разместить меню в action bar (по версии перевода Google "строка действий").
        // По мне так строка действий звучит еще запутанней, чем Action bar
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
        // Вы это можете указать в родительском активити в файле манифеста.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
