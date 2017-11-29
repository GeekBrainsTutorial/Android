package ru.geekbrains.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Меню Action bar - установка меню в action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Меню Action bar - выбор пункта меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                addElement();
                return true;
            case R.id.menu_clear:
                clearList();
                return true;
            case R.id.menu_info:
                info();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addElement() {
        Toast.makeText(this, "Добавление элемента", Toast.LENGTH_SHORT).show();
    }

    private void clearList(){
        Toast.makeText(this, "Очистка списка", Toast.LENGTH_SHORT).show();
    }

    private void info(){
        Toast.makeText(this, "Информация", Toast.LENGTH_SHORT).show();
    }
}
